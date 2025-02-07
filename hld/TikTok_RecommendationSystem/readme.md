## Tiktok monolith

| **Goal** :: Recommend videos to users based on their preferences and changine behaviour("content-drift") |

- Realtime recommendation system - ML embedding model based approach
  - Many systems work in batch mode, but Tiktok works in realtime to accomodate "content-drift"
  - Fault tolerance is relaxed in favor of real-time performance
  - All entities and relevant embeddings are stored in memory for quick access

### Embedding models

https://huggingface.co/blog/getting-started-with-embeddings

- Best way to visualize them is to think of them as a vector in n-dimensional space where each video is represented as a point in this space. The distance between two points is a measure of similarity between the two videos. 

- One way I like to think of a video's embedding as a hashed value that's mapped to it's characteristics that uniquely identifies the video. I've been using vector databases for a while, and that's how I use them.

This is the basic premise behind vector-databases. I think within the tiktok monilith, they use a similar approach to recommend videos.

There's similar models/functionalities I've worked with in past like word2vec, elastic-search SVMs, etc.

**How can Tiktok possibly map user preferences to videos with embeddings?**

Now embedding's are multi-faceted. Same embed-model can be used to generate embeds from multiple modalities. Like a given piece of text that describes a video can be used to generate an embedding. Similarly, the video itself can be used to generate an embedding. And if these two embeddings are close to each other, then the video is recommended if user types in a similar text. 

Same can be said for user preferences. The user behaviour(likes, dislikes, comments, stay-duration, etc) can be used to generate an embedding. And if this embedding is close to a set of video-embeddings, then the set of videos are recommended.

p.s. to make such models, the models have to be trained on those multiple modalities.

### Hash table space optimization to accomodate in-memory storage

- Storing embedding tables in memory for quick access and quick computation
- We want to save as much space as possible
  - Using **cuckoo hashing**
  - Only include frequently appearing IDs(of users, videos, etc) in the hash table
    - If a user hasn't been active for a while, then the user's embedding won't effect the embedding models significantly and hence can be ignored
    - Similarly for videos, if a video hasn't been watched for a while

**Cuckoo hashing**

- Monolith employs cuckoo hashing to reduce hash-collisions and make the most of in-memory storage
- Basically if hash-collision occurs(for A let's say at B), the hash B is rehashed and stored in another location, the hash C is stored in another and so on... until a free hashed-slot is found. This all is done with multiple hash-functions cyclically

h0(x) = hash function 1
h1(x) = hash function 2

TC: O(1) insert, delete, search; amortized

### HLD architecture within the paper

- Training data formation
  - User behaviour is captured in real-time and partitioned into multiple streams on Kafka
    - Both +ve and -ve feedback is captured
    - -ve feedback volume is high hence, downsampled and aggregated
    - **+ve feedback volume can vary, hence a `correcting-factor` has to be applied to +ve:-ve feedback ratio at inference time**
  - User requests/embeds grabbed are also dumped into Kafka
  - Each event has a `request_id` which is hashed to generate `patitionID` used to perform joins on the streams using Apache Flink
    - Apache Flink uses disk if it can't fit the data in memory
- Training parameter server - in-memory storage we discussed above
  - The embeddings are stored in-memory and are updated in real-time
  - Streams the parameters to inference-parameter server at 1 min frequency
  - Inference parameter server is then used by the inference engine(actual embedding model and recommendation engine) to generate recommendations
  - Fault tolerance for each PS:
    - Handled via `checkpointing` and `savepoints`
    - A Goldilocks zone has to be maintained between the two - too frequent higher system load, too infrequent weaker inference
      - Think in terms of faults or server faults
      - If too frequent, then system will be under constant load to save the state despite having most recent data
      - If too infrequent, then the system will have to retrain the model from scratch with stale data
    - For significant metrics
- Questions???
  - Why used Apache Flink??? Why not Kafka Streams or Spark Streaming?
  - Online training vs. Offline(periodic) training

**Checkpointing**

- **Dense parameters are synced once a day**
  - These parameters don't change much
  - Represented by batch training data
  - Synced to inference-PS once a day
- **Sparse parameters are synced every 1 min**
  - *Only send the deltas(modified entity IDs) to the PS*
  - These parameters change a lot and are updated in near real-time
    - Model quality quickly degrades if not updated in real-time, hence such high frequency
  - Synced to inference-PS every 1 min

- Checkpoint all parameters once a day at <u>Low load</u>
- Mentioned metric - 1 in 1000 PS fails every 10 days, yet no signifcant decrease in model quality
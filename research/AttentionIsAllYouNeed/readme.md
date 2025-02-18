# Attention is all you need

## Basic premise

- Several different models were used for **natural language processing**, such as RNNs, LSTMs, GRUs, etc. and even CNNs, AutoEncoders, etc.
- Using above models in one way or the other the full semantic context of a sentence was never captured.
- Also, the models mentioned above(particularly RNNs) were slow to train due to their sequential feed-forward networks
- But the transformer model was the first to use **attention** as a mechanism to learn the dependencies between words in a sentence.

**Attention** is essentially a way to parallelize the learning of dependencies between different semantic contexts in a sentence. This can be extended to multiple modalities like images, videos, etc. as well.

> Since it's parallize-able, using GPUs the training time can be drastically reduced.

### Some details

> When it comes to transformer models, bigger the model, bigger the dataset, better the performance. Though with diminishing returns.

### Mathematical model components

**Softmax function** is used for generating attention scores/probabilities for each word/token in a sentence. WHY softmax? Because it generates a probability distribution over the input values and it's easier to do gradient descent on it... during backpropagation.

**Positional embeddings** are used to encode the position of the words in a sentence. This is used to generate the positional encoding matrix, which is then added to the input embeddings of the model. This is used to generate the `E = E + P` matrix. This can help the model to understand the context of the sentence using the position of the words in the sentence.

**Query encoding matrix** are values of the word queries in a sentence. These can assumed to be the queries to the input embeddings of the model, which is querying what's the context of the sentence/token. Like existence of a *tree* and a *monkey* in a sentence.

The embeddings are the same as in the word embeddings we use in word2vec, GloVe, etc., which are multiplied by the query matrix to generate `Q = E . W_q` matrix.

**Key encoding matrix** are the keys to the input embeddings of the model, which is used to generate the `K = E . W_k` matrix. This matrix can be thought of as the matrix which attempts to find the key aspects of the context items to the sentence. Like existence of a *tree* and a *monkey* being *green* and *brown* respectively.

This relationship between the query and key matrices better understood via the **dot-product** of the two matrices. This is then scaled by the square root of the dimension of the key matrix along with softmax function to generate the attention scores.

Scaling is done to avoid the vanishing gradient problem.

**Masked multi-headed attention** :: **Used to prevent later tokens impacting current results.** This is done by setting the attention scores of the later tokens to `-inf` before applying the softmax function. This is done to force softmax to give a probability of `0` to the later tokens.

> This can cause trouble in higher context windows, due to wasted compute, hence sth clever must be being done by current LLMs with larger context windows. p.s. i'm currently at a point where GPT-o1 was released a couple months ago.

❓ Since for a certain number of tokens/words outputs are already with us, we can perform some caching to prevent the re-computation of the same values. ??? A big question mark here.

**Value encoding matrix** are the values of the word queries in a sentence. These generate `V = E . W_v` matrix. This matrix can be thought of as the matrix which attempts to find the co-relative aspects of various entities in the sentence. Like relationship between a *tree* and a *monkey* *sitting* on it.

Now, the key, query and value matrices can try to infer different aspects or meanings which can drive output. (In more ways than i discussed it). How and what the model actually learns is a big question mark❓.

**Multi-Headed Attention** :: All above described one single attention head. But multiple attention heads are used to learn different aspects of the sentence. This is done by splitting using multiple key, query and value matrices to build multiple heads and then concatenating the results of the multiple heads to generate the final output.

**Feed Forward Neural Network** :: The output of the multi-headed attention is then passed through a feed-forward neural network to generate the final output. It's defined well within the papar. 

It's a simple feed-forward network with a ReLU activation function.

f(x) = ReLU(x . W_1 + b_1) . W_2 + b_2

**Layer Normalization** :: This is used to normalize the output of the feed-forward network. This is done to prevent the vanishing gradient problem.


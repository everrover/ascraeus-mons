# Notes #1

## Designing live-comments/interactions feature for streams and videos

### Scale of the system

- 1M users viewing a stream(worse case) - 1hr streams make up bulk of the content
- 5% users actively commenting on a stream - a comment every 10 seconds [80/20 rule]
  - 50k comments every 10 seconds
  - 360 * 50k = 18M comments/hr
  - 18M * 2KB = 36GB/hr
- 1 comment = 2KB => 36GB/hr per video/stream
- 10K streams running concurrently, 20% forming bulk of the traffic
  - 10K * 24 * 0.2 * 36GB = 172.8TB/day

### Functional requirements

- Post a comment
- View comments

---

- Edit/Delete a comment
- Add likes/dislike to comment
- View older comments
- Reply to a comment
- Bulk process comments
  - Sentiment analysis
  - Spam detection
  - Moderation
  - ...
- LTS - Long term storage
- Search comments

### Non-functional requirements

- CAP theorem
  - Availability > Consistency
- Eventual consistency is acceptable
- High scalability
- Low read/write latency

---

- Security
- Integrity

### API's interface

Posting a comment
- POST /comments/:stream_id
  - Request: {comment: "comment", user_id: "user_id", ...}
  - Response: {comment_id: "comment_id", status: "success/failure", ...}
- GET /comments/:stream_id
  - Request: {page: "page", ...}
  - Response: {comments: [{comment_id: "comment_id", comment: "comment", ...}, ...], ...}
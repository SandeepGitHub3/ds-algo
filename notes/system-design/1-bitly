#  Bitly 

https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly

## Introduction
Bit.ly is a URL shortening service that converts long URLs into shorter, manageable links. It also provides analytics for the shortened URLs.

## Requirements
![alt text](image.png)

## Core Entities
![alt text](image-1.png)

## API

```
// Shorten a URL
POST /urls
{
  "long_url": "https://www.example.com/some/very/long/url",
  "custom_alias": "optional_custom_alias",
  "expiration_date": "optional_expiration_date"
}
->
{
  "short_url": "http://short.ly/abc123"
}
```

```
// Redirect to Original URL
GET /{short_code}
-> HTTP 302 Redirect to the original long URL
```

## HLD
![alt text](image-2.png)

There are two main types of HTTP redirects that we could use for GET APi

1. **301 (Permanent Redirect)**: This indicates that the resource has been permanently moved to the target URL. Browsers typically cache this response, meaning subsequent requests for the same short URL might go directly to the long URL, bypassing our server.

        HTTP/1.1 301 Moved Permanently
        Location: https://www.original-long-url.com


2. **302 (Temporary Redirect):** This indicates that the resource is temporarily located at the target URL. Browsers do not cache this response, meaning every request for the short URL will hit our server.
The response back to the client looks like this:

        HTTP/1.1 302 Found
        Location: https://www.original-long-url.com   

For a URL shortener, a 302 redirect is often preferred because:
-  It gives us more control over the redirection process, allowing us to update or expire links as needed.
- It prevents browsers from caching the redirect, which could cause issues if we need to change or delete the short URL in the future.
- It allows us to track click statistics for each short URL (even though this is out of scope for this design).

## Deep Dives

### 1. Ensuring Unique Short URLs in a URL Shortener System

#### Constraints
- Ensure **uniqueness** of short codes.
- Keep URLs **as short as possible**.
- Generate codes **efficiently and at scale**.

---

#### ❌ Bad Approach: Using URL Prefix
- Taking first N characters of the original URL (e.g., `"www.link..."`).
- ❗ Not unique — high chance of collision.
- ❗ Fails to distinguish between similar URLs.

---

#### ✅ Good Approaches

1. Random Number + Base62 Encoding
- Generate a random number and encode using Base62.
- Use first N characters as the short code.
- Compact and easy to implement.

**Challenges:**
- Risk of **collisions** (Birthday Problem).
- Requires **database check** for each new URL.
- More entropy → longer codes → less "short".

---

2. Hashing (e.g., SHA or MD5) + Base62
- Deterministic mapping: same URL → same code.
- High entropy → low collision probability.

**Challenges:**
- Collisions still possible.
- Hard to reverse.
- Cannot easily identify duplicates unless standardized.

---

#### Great  - Counter + Base62 (Preferred)
- Use a global **incrementing counter**.
- Encode counter in Base62 (e.g., `1,000,000,000` → `15ftgG`).
- Each short URL is **unique** and **sequential**.

**Advantages:**
- No collision checks needed.
- Fast, simple, and scalable.
- Easy to decode if necessary.

**Challenges:**
- Requires atomic counter management in **distributed systems**.
- Counter size grows over time, but:
  - 1B URLs = 6-character code.
  - Up to 3.5 trillion URLs = 7 characters.

**Tools:**
- Use **Redis** with `INCR` for atomic counter updates.

---

### 2. How can we ensure that redirects are fast?
---
#### Good Solution: Add Indexes to the Database
- **B-tree Indexing**: Enables O(log n) lookup on short URL codes.
- **Primary Key**: Set `short_code` as the primary key for built-in indexing and uniqueness.
- **Hash Indexing**: Ideal for exact match lookups; offers O(1) average time (e.g., in PostgreSQL).

Database Limitations
- Even SSDs (~100K IOPS) struggle with massive read throughput.
- At 100M DAU × 5 redirects = 500M/day (~600K/sec during spikes).
---
#### Greate Solution: In-Memory Cache (Redis, Memcached)**
- Cache short-to-long URL mappings.
- **Hit**: Fast redirect via memory.
- **Miss**: Query DB and populate cache.

Cache Performance
- **Memory**: ~0.0001 ms access, millions of ops/sec.
- **SSD**: ~0.1 ms, ~100K IOPS.
- **HDD**: ~10 ms, ~100 IOPS.

Challenges
- Cache invalidation
- Cold starts (warming up)
- Memory limits and eviction policy (e.g., LRU)
---
#### Great Solution: Edge Optimization with CDN & Edge Functions
- Use **CDN PoPs** to cache and redirect near the user.
- Deploy redirect logic using **Cloudflare Workers**, **Lambda@Edge**, etc.

Pros
- Low latency
- Offloads primary servers

Cons
- Complexity in cache consistency
- Edge limitations (e.g., memory, debug tools)
- Cost

### 3. Scaling for 1B Shortened URLs & 100M DAU

Storage Size Estimation:
- **Per Row Estimate**: ~500 bytes (short code, long URL, timestamps, metadata)
- **Total for 1B URLs**: 500B → **~500GB**
- Modern SSDs can easily handle this capacity

 🗄️ Database Selection
- **Write Load is Low**: ~100K new URLs/day ≈ ~1 write/sec
- **Any RDBMS works**: Postgres, MySQL, DynamoDB, etc.
- ✅ Go with **Postgres** if unsure (strong community + replication support)

📉 Handling DB Failures
- **Replication**: Use replicas for failover
- **Backups**: Periodic snapshots for disaster recovery


🔃 Read vs Write Separation
- **Read Service**: Handles redirects (read-heavy)
- **Write Service**: Handles URL creation (write-light)
- Scale **independently** via **horizontal scaling**

🔢 Counter for Unique Short Codes

❗ Challenge
- Write service is horizontally scaled
- We need **globally unique short codes**
  

 ✅ Solution: Redis Counter
- Centralized Redis holds the counter
- Use `INCR` for atomic increment operations
- Each Write Service fetches the next counter value and generates a code

 🚀 Optimizing with Counter Batching

- Each Write Service fetches a **batch** (e.g., 1000 values) from Redis
- Redis increments counter by batch size and returns starting value
- Reduces network calls, maintains uniqueness
- Local usage of batch = faster URL creation

🔄 Redis Reliability
- Use **Redis replication & failover**
- Persist counter value periodically for durability

## Final Design
![alt text](image-3.png)

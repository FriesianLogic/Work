# Volume 7: Kernel-Level Integration
## 7.1 Dynamic OOM_ADJ Scoring
The Arbitration Service pushes state updates to the ActivityManagerService to adjust process priority.

| Vendor Phase | Adjusted Priority | oom_score_adj |
| :--- | :--- | :--- |
| RENDERING | FOREGROUND_APP | 0 |
| STAGING | PERCEPTIBLE_APP | 200 |
| REFRESHING | HEAVY_WEIGHT_APP | 400 |
| IDLE | CACHED_APP | 900+ |

This ensures the kernel protects "Ready" vendors while aggressively reclaiming memory from "Idle" ones.
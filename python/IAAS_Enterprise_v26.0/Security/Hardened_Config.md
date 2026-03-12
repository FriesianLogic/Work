# Volume 3: Hardened Configuration
## 3.1 Production Thresholds
| Parameter | Default Value | Purpose |
| :--- | :--- | :--- |
| REFRESH_TOKEN_TTL | 60s | Prevents radio-state "tail" drain. |
| STAGE_TOKEN_TTL | 30s | Covers complex layout inflation. |
| RENDER_TOKEN_TTL | 15s | Maximum interaction window. |
| MAX_BINDER_CALLS_PER_SEC | 20 | Prevents IPC flooding. |

## 3.2 Security Enforcement
- **Signature Pinning**: Validates SHA-256 hash of the app's signing certificate.
- **UID Binding**: Uses `Binder.getCallingUid()` to prevent session hijacking.
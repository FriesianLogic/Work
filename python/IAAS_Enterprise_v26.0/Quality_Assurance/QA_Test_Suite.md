# Volume 9: QA & Unit Tests
## 9.1 Test: The Stalling Stager
**Goal**: Verify 30s TTL enforcement.
**Method**: Grant STAGE token; block `signalPhaseComplete()`.
**Assertion**: Watchdog must trigger `onCapabilityRevoked` at 30,001ms.

## 9.2 Test: Refresh Storm Mitigation
**Goal**: Verify throttling.
**Method**: 10 simultaneous refresh requests.
**Assertion**: First 2 granted; remaining 8 receive `RETRY_AFTER` with exponential backoff.
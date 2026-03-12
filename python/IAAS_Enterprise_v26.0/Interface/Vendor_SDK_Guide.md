# Volume 5: Vendor SDK Reference
## 5.1 Implementing the Three-Phase State Machine
- **Phase I (Refresh)**: Request `TYPE_REFRESH`. Perform network I/O only after grant.
- **Phase II (Stage)**: Request `TYPE_STAGE`. Perform image decoding and layout inflation.
- **Phase III (Render)**: Exclusive surface window. You must release the surface within 15s or face a Watchdog timeout.

## 5.2 Error Handling
Apps must implement `onCapabilityRevoked`. If revoked, stop all threads immediately to avoid battery penalties and score degradation.
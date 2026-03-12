# Volume 2: The Governor Logic
## 2.1 The Arbitration Engine Loop
The Engine executes a deterministic scheduler loop every 500ms to resolve scores and transition vendor states.

## 2.2 Scoring Algorithm
Rank ($R$) is calculated for every "Ready" vendor:
$$R = (W_{policy} \times \ln(T_{starve})) - (C_{sys} \times R_{hist})$$

- **W_policy**: Multiplier delivered via server-side policy (e.g., 1.5 for Premium partners).
- **T_starve**: Time in seconds since the vendor last occupied the surface.
- **C_sys**: System contention factor (current thermal/CPU load).
- **R_hist**: Historical resource risk score of the specific vendor package.
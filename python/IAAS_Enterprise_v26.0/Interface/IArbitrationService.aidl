package android.os.arbitration;
import android.os.arbitration.IArbitrationCallback;

/**
 * Interface for third-party vendors to coordinate resource access.
 */
interface IArbitrationService {
    // Phase 0: Registration & Security Handshake
    long registerVendor(in String packageName, in byte[] signatureHash);

    // Phase 1-3: Tokenized Requests
    // Types: 1=REFRESH, 2=STAGE, 3=RENDER
    void requestCapability(long sessionId, int type);

    // Lifecycle: Release current token to unblock the scheduler
    void signalPhaseComplete(long sessionId, String tokenId);

    // Observability: Push performance data for scoring
    void reportTelemetry(long sessionId, in Bundle metrics);

    // Listener: Register for asynchronous grant/revocation events
    void setCallback(long sessionId, IArbitrationCallback callback);
}
package ratelimiterlld;

public enum Algorithm {
    FIXED_WINDOW,
    SLIDING_WINDOW_LOG,
    LEAKY_BUCKET,
    TOKEN_BUCKET
}

package net.imshit.scoreboard;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ScoreInfo (
        String name,
        int score,
        LocalDateTime time
) implements Serializable {}

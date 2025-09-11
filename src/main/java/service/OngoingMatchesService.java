package service;

import dao.IMatchDao;
import dao.Impl.MatchDao;
import exception.NotFoundException;
import model.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static exception.ErrorMessages.MATCH_NOT_FOUND;

public class OngoingMatchesService {

    private final Map<UUID, Match> ongoingMatches;

    public OngoingMatchesService() {
        this.ongoingMatches = new ConcurrentHashMap<>();
    }
    public UUID add(Match match) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, match);
        return uuid;
    }
    public Match getMatch(UUID uuid) throws NotFoundException {
        return Optional.ofNullable(ongoingMatches.get(uuid))
                .orElseThrow(() -> new NotFoundException(MATCH_NOT_FOUND));
    }

    public void delete(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}

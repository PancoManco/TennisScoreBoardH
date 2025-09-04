package service;

import dao.IMatchDao;
import dao.Impl.MatchDao;
import model.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private final Map<UUID, Match> ongoingMatches = new ConcurrentHashMap<>();

    public UUID add(Match match) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, match);
        return uuid;
    }
    public Optional<Match> getMatch(UUID uuid) {
        return Optional.ofNullable(ongoingMatches.get(uuid));
    }
    public void delete(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}

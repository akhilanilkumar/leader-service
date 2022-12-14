package com.leader.leaderservice.exception;

public class NoSuchPartyExistException extends Exception {
    public NoSuchPartyExistException(Long partyId) {
        super(String.format("No matching records found for a Party: %d", partyId));
    }
}

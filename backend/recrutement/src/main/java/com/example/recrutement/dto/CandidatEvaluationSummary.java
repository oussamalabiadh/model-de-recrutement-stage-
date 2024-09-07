package com.example.recrutement.dto;
public class CandidatEvaluationSummary {
    private Long candidatId;
    private int numberOfFiles;
    private int totalGlobalNote;

    // Constructor, getters, and setters
    public CandidatEvaluationSummary(Long candidatId, int numberOfFiles, int totalGlobalNote) {
        this.candidatId = candidatId;
        this.numberOfFiles = numberOfFiles;
        this.totalGlobalNote = totalGlobalNote;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public int getTotalGlobalNote() {
        return totalGlobalNote;
    }

    public void setTotalGlobalNote(int totalGlobalNote) {
        this.totalGlobalNote = totalGlobalNote;
    }
}

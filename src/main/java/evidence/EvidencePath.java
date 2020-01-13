package evidence;

public final class EvidencePath {

    private String testClassName;
    private String testCaseName;

    protected EvidencePath() {
    }

    public String getTestClassName() {
        return testClassName;
    }

    protected void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    protected void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

}

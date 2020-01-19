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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        EvidencePath castObject = (EvidencePath) object;
        if (!this.testClassName.equals(castObject.testClassName)) {
            return false;
        }
        if (!this.testCaseName.equals(castObject.testCaseName)) {
            return false;
        }
        return true;
    }

}

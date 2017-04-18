/**
 * @author Jp
 *
 */
package com.sci.testamigo.usermanagement.db;
import java.sql.Timestamp;

public class TestSuiteDetails implements java.io.Serializable {
  private static final long serialVersionUID = -515361172904812545L;
  private long testSuiteDetailsId;
  private String testSuiteName;
  private String testSuiteUnqName;
  private Timestamp dateTime;
  public long getTestSuiteDetailsId() {
    return testSuiteDetailsId;
  }
  public void setTestSuiteDetailsId(long testSuiteDetailsId) {
    this.testSuiteDetailsId = testSuiteDetailsId;
  }
  public String getTestSuiteName() {
    return testSuiteName;
  }
  public void setTestSuiteName(String testSuiteName) {
    this.testSuiteName = testSuiteName;
  }
  public String getTestSuiteUnqName() {
    return testSuiteUnqName;
  }
  public void setTestSuiteUnqName(String testSuiteUnqName) {
    this.testSuiteUnqName = testSuiteUnqName;
  }
  public Timestamp getDateTime() {
    return dateTime;
  }
  public void setDateTime(Timestamp dateTime) {
    this.dateTime = dateTime;
  }
}
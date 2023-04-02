class Staff implements Comparable<Object> {

    private int empNo;
    private String fName;
    private String sName;
    private String department;
    private float wage;
    private float projectCompletionRate;


    // constructor
    public Staff(int empNo, String fName, String sName, String department, float wage, float projectCompletionRate) {
        super();
        this.empNo = empNo;
        this.fName = fName;
        this.sName = sName;
        this.department = department;
        this.wage = wage;
        this.projectCompletionRate = projectCompletionRate;
    }

    // setters and getters
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getProjectCompletionRate() {
        return projectCompletionRate;
    }

    public void setProjectCompletionRate(float projectCompletionRate) {
        this.projectCompletionRate = projectCompletionRate;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }


    @Override
    public int compareTo(Object obj) {

		/*
		Edit this section so it compares the appropriate
		column you wish to sort by
		*/

        if (obj == this){
            return 0;
        }

        if (obj instanceof Staff){
            Staff sff = (Staff) obj;
            int value = Float.compare(this.wage, sff.wage);
            if (value != 0){
                return value;
            }

            return empNo - (sff.getEmpNo());
        }

        throw new RuntimeException("Class type mismatch.");

    }

    @Override
    public String toString() {
        return "Staff [EmpNo=" + empNo + ", first name=" + fName + ", last Name=" + sName + ", department="
                + department + ", wage=" + wage + ", project completion rate="
                + projectCompletionRate + "]";
    }
}

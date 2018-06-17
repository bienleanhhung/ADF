package view;

import oracle.adf.view.rich.context.AdfFacesContext;


public class EmpDetail {
    public EmpDetail() {
        super();
    }
    private Number empId;

    public void setEmpId(Number empId) {
        this.empId = empId;
    }

    public Number getEmpId() {
        return empId;
    }


    public void onDepartmentChange(final Number empId) {
        System.out.println("onDepartmentExchange called: " + empId);
        setEmpId(empId);
        TabBean viewTab = (TabBean) JSFUtils.resolveExpression("#{TabBean}");
        viewTab.getEmpTab().setDisclosed(false);
        viewTab.getEmpDetailTab().setDisclosed(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(viewTab.getPanelTabbed());
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("EmpDetail finalized: " + this);
        super.finalize();
    }
}

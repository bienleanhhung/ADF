package view;

import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

public class TabBean {
    private RichShowDetailItem empTab;
    private RichShowDetailItem empDetailTab;
    private RichPanelTabbed panelTabbed;

    public TabBean() {
        super();
    }

    public void setEmpTab(RichShowDetailItem empTab) {
        this.empTab = empTab;
    }

    public RichShowDetailItem getEmpTab() {
        return empTab;
    }

    public void setEmpDetailTab(RichShowDetailItem empDetailTab) {
        this.empDetailTab = empDetailTab;
    }

    public RichShowDetailItem getEmpDetailTab() {
        return empDetailTab;
    }

    public void setPanelTabbed(RichPanelTabbed panelTabbed) {
        this.panelTabbed = panelTabbed;
    }

    public RichPanelTabbed getPanelTabbed() {
        return panelTabbed;
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println("TabBean finalized: " + this);
        super.finalize();
    }

}

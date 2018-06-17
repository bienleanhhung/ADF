package view;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.share.security.SecurityContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class CheckStatus {
    public CheckStatus() {
        super();
    }
    private Boolean consultationMode = true;

    public void setConsultationMode(Boolean consultationMode) {
        this.consultationMode = consultationMode;
    }

    public Boolean getConsultationMode() {
        return consultationMode;
    }

    public String getModeEdit() {
        if (consultationMode == true)
            return "single";
        else
            return "none";
    }

    public Boolean checkUser() {
        SecurityContext sec = ADFContext.getCurrent().getSecurityContext();
        if (sec.isUserInRole("Admin"))
            return true;
        return false;
    }

    public void setModeCreate(ActionEvent actionEvent) {
        // Add event code here...
        if (checkUser()) {
            ADFUtil.invokeEL("#{bindings.Create.execute}");
            setConsultationMode(false);
        }
    }

    public void setModeEdit(ActionEvent actionEvent) {
        // Add event code here...
        if (checkUser()) {
            setConsultationMode(false);
        }
    }

    public void setModeCancel(ActionEvent actionEvent) {
        // Add event code here...
        if (checkUser()) {
            setConsultationMode(true);
            ADFUtil.invokeEL("#{bindings.Execute.execute}");
            ADFUtil.invokeEL("#{bindings.Rollback.execute}");
        }

    }

    public void setModeSave(ActionEvent actionEvent) {
        // Add event code here...
        if (checkUser()) {
            ADFUtil.invokeEL("#{bindings.Commit.execute}");
            setConsultationMode(true);
        }


    }

    public void setModeDelete(ActionEvent actionEvent) {
        // Add event code here...
        if (checkUser()) {
            ADFUtil.invokeEL("#{bindings.Delete.execute}");
            setConsultationMode(false);
        }
    }

    public BindingContainer getBingdings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void dialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        if (checkUser()) {
            if (dialogEvent.getOutcome().name().equals("ok")) {
                ADFUtil.invokeEL("#{bindings.Delete.execute}");
                BindingContainer bingding = getBingdings();
                OperationBinding operationBingding = bingding.getOperationBinding("Commit");
                operationBingding.execute();

            } else
                return;
        }
    }

    private void refreshView() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String cv = ctx.getViewRoot().getViewId();
        ViewHandler vh = ctx.getApplication().getViewHandler();
        UIViewRoot ui = vh.createView(ctx, cv);
        ctx.setViewRoot(ui);
    }


}

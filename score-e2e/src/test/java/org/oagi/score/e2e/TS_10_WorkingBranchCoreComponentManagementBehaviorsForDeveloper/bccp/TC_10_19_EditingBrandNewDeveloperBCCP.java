package org.oagi.score.e2e.TS_10_WorkingBranchCoreComponentManagementBehaviorsForDeveloper.bccp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.oagi.score.e2e.BaseTest;
import org.oagi.score.e2e.api.CoreComponentAPI;
import org.oagi.score.e2e.obj.*;
import org.oagi.score.e2e.page.HomePage;
import org.oagi.score.e2e.page.core_component.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomPrint;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.*;
import static org.oagi.score.e2e.AssertionHelper.*;
import static org.oagi.score.e2e.impl.PageHelper.getText;
import static org.oagi.score.e2e.impl.PageHelper.visibilityOfElementLocated;

@Execution(ExecutionMode.CONCURRENT)
public class TC_10_19_EditingBrandNewDeveloperBCCP extends BaseTest {

    private List<AppUserObject> randomAccounts = new ArrayList<>();

    @BeforeEach
    public void init() {
        super.init();
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();

        // Delete random accounts
        this.randomAccounts.forEach(randomAccount -> {
            getAPIFactory().getAppUserAPI().deleteAppUserByLoginId(randomAccount.getLoginId());
        });
    }

    private void thisAccountWillBeDeletedAfterTests(AppUserObject appUser) {
        this.randomAccounts.add(appUser);
    }

    @Test
    public void test_TA_10_19_1_a() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        assertFalse(bccpPanel.getGUIDField().isEnabled());
        assertFalse(bccpPanel.getDENField().isEnabled());
        assertDisabled(bccpPanel.getDeprecatedCheckbox());
    }

    @Test
    public void test_TA_10_19_1_b() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        assertTrue(bccpPanel.getPropertyTermField().isEnabled());
        assertEnabled(bccpPanel.getNillableCheckbox());
        assertTrue(bccpPanel.getValueConstraintSelectField().isEnabled());
        assertTrue(bccpPanel.getNamespaceSelectField().isEnabled());
        assertTrue(bccpPanel.getDefinitionField().isEnabled());
        assertTrue(bccpPanel.getDefinitionSourceField().isEnabled());

        String randomPropertyTerm = randomAlphabetic(5, 10).replaceAll(" ", "");
        randomPropertyTerm = Character.toUpperCase(randomPropertyTerm.charAt(0)) + randomPropertyTerm.substring(1).toLowerCase();
        bccpPanel.setPropertyTerm("Test Object " + randomPropertyTerm);
        bccpPanel.toggleNillable();
        String namespace = "http://www.openapplications.org/oagis/10";
        bccpPanel.setNamespace(namespace);
        String definition = randomPrint(50, 100).trim();
        bccpPanel.setDefinition(definition);

        assertTrue(bccpViewEditPage.getUpdateButton(true).isEnabled());
    }

    @Test
    public void test_TA_10_19_1_c() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        String randomPropertyTerm = randomAlphabetic(5, 10).replaceAll(" ", "");
        randomPropertyTerm = Character.toUpperCase(randomPropertyTerm.charAt(0)) + randomPropertyTerm.substring(1).toLowerCase();
        bccpPanel.setPropertyTerm("Test Object " + randomPropertyTerm);

        String denText = getText(bccpPanel.getDENField());
        assertEquals("Test Object " + randomPropertyTerm + ". Code", denText);
    }

    @Test
    public void test_TA_10_19_1_d() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        bccpPanel.setValueConstraint("Fixed Value");
        String fixedValue = randomAlphabetic(5, 10);
        bccpPanel.setFixedValue(fixedValue);

        bccpPanel.setValueConstraint("Default Value");
        String defaultValue = randomAlphabetic(5, 10);
        bccpPanel.setDefaultValue(defaultValue);

        bccpPanel.setValueConstraint("Fixed Value");
        assertTrue(isEmpty(getText(bccpPanel.getFixedValueField())));

        bccpPanel.setValueConstraint("Default Value");
        assertTrue(isEmpty(getText(bccpPanel.getDefaultValueField())));
    }

    @Test
    public void test_TA_10_19_1_e() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        bccpPanel.setValueConstraint("Fixed Value");
        String fixedValue = randomAlphabetic(5, 10);
        bccpPanel.setFixedValue(fixedValue);

        bccpPanel.toggleNillable();

        String valueConstraintSelectText = getText(bccpPanel.getValueConstraintSelectField());
        assertEquals("None", valueConstraintSelectText);

        bccpPanel.setValueConstraint("Fixed Value");
        bccpPanel.setFixedValue(fixedValue);
        assertNotChecked(bccpPanel.getNillableCheckbox());
    }

    @Test
    public void test_TA_10_19_1_f() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        String randomPropertyTerm = randomAlphabetic(5, 10).replaceAll(" ", "");
        randomPropertyTerm = Character.toUpperCase(randomPropertyTerm.charAt(0)) + randomPropertyTerm.substring(1).toLowerCase();
        bccpPanel.setPropertyTerm("Test Object " + randomPropertyTerm);
        bccpPanel.toggleNillable();
        String namespace = "http://www.openapplications.org/oagis/10";
        bccpPanel.setNamespace(namespace);

        assertThrows(TimeoutException.class, () -> bccpViewEditPage.hitUpdateButton());
        assertEquals("Update without definitions.", getText(visibilityOfElementLocated(getDriver(),
                By.xpath("//mat-dialog-container//div[contains(@class, \"header\")]"))));
    }

    @Test
    public void test_TA_10_19_1_g() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.DTPanel dtPanel = bccpViewEditPage.getBCCPPanelContainer().getDTPanel();

        assertFalse(dtPanel.getCoreComponentField().isEnabled());
        assertEquals("DT", getText(dtPanel.getCoreComponentField()));
        assertFalse(dtPanel.getReleaseField().isEnabled());
        assertEquals(getText(bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel().getReleaseField()),
                getText(dtPanel.getReleaseField()));
        assertFalse(dtPanel.getRevisionField().isEnabled());
        assertFalse(dtPanel.getStateField().isEnabled());
        assertEquals("Published", getText(dtPanel.getStateField()));
        assertFalse(dtPanel.getOwnerField().isEnabled());
        assertFalse(dtPanel.getGUIDField().isEnabled());
        assertFalse(dtPanel.getDENField().isEnabled());
        assertEquals("System Environment_ Code. Type", getText(dtPanel.getDENField()));
        assertFalse(dtPanel.getDataTypeTermField().isEnabled());
        assertEquals("Code", getText(dtPanel.getDataTypeTermField()));
        assertFalse(dtPanel.getQualifierField().isEnabled());
        assertEquals("System Environment", getText(dtPanel.getQualifierField()));
        assertFalse(dtPanel.getDefinitionSourceField().isEnabled());
        assertFalse(dtPanel.getDefinitionField().isEnabled());
    }

    @Test
    public void test_TA_10_19_1_h() {
        AppUserObject endUser = getAPIFactory().getAppUserAPI().createRandomEndUserAccount(false);
        thisAccountWillBeDeletedAfterTests(endUser);

        NamespaceObject endUserNamespace = getAPIFactory().getNamespaceAPI().createRandomEndUserNamespace(endUser);

        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();

        assertThrows(TimeoutException.class, () -> bccpPanel.setNamespace(endUserNamespace.getUri()));
    }

    @Test
    public void test_TA_10_19_2() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        BCCPViewEditPage bccpViewEditPage = bccpCreateDialog.create("System Environment_ Code. Type");
        BCCPChangeBDTDialog bccpChangeBDTDialog = bccpViewEditPage.openChangeBDTDialog();
        String nextBDTDen = "Telephone_ Value. Type";
        bccpChangeBDTDialog.update(nextBDTDen);

        BCCPViewEditPage.DTPanel dtPanel = bccpViewEditPage.getBCCPPanelContainer().getDTPanel();
        assertEquals(nextBDTDen, getText(dtPanel.getDENField()));
        assertEquals("Value", getText(dtPanel.getDataTypeTermField()));
        assertEquals("Telephone", getText(dtPanel.getQualifierField()));
    }

    @Test
    public void test_TA_10_19_3() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);
        AppUserObject anotherDeveloper = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(anotherDeveloper);
        AppUserObject endUser = getAPIFactory().getAppUserAPI().createRandomEndUserAccount(false);
        thisAccountWillBeDeletedAfterTests(endUser);

        String branch = "Working";
        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPCreateDialog bccpCreateDialog = viewEditCoreComponentPage.openBCCPCreateDialog(branch);
        bccpCreateDialog.create("Temperature_ Open_ Measure. Type");
        viewEditCoreComponentPage.openPage();

        String den = "Property Term. Measure";
        {
            viewEditCoreComponentPage.setDEN(den);
            viewEditCoreComponentPage.hitSearchButton();

            WebElement tr = viewEditCoreComponentPage.getTableRecordByValue(den);
            WebElement td = viewEditCoreComponentPage.getColumnByName(tr, "transferOwnership");
            assertTrue(td.findElement(By.tagName("button")).isEnabled());

            TransferCCOwnershipDialog transferCCOwnershipDialog =
                    viewEditCoreComponentPage.openTransferCCOwnershipDialog(tr);
            transferCCOwnershipDialog.transfer(anotherDeveloper.getLoginId());

            viewEditCoreComponentPage.setDEN(den);
            viewEditCoreComponentPage.hitSearchButton();

            tr = viewEditCoreComponentPage.getTableRecordByValue(den);
            td = viewEditCoreComponentPage.getColumnByName(tr, "owner");
            assertEquals(anotherDeveloper.getLoginId(), getText(td));
        }

        homePage.logout();
        homePage = loginPage().signIn(anotherDeveloper.getLoginId(), anotherDeveloper.getPassword());
        viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        {
            viewEditCoreComponentPage.setDEN(den);
            viewEditCoreComponentPage.hitSearchButton();

            WebElement tr = viewEditCoreComponentPage.getTableRecordByValue(den);
            WebElement td = viewEditCoreComponentPage.getColumnByName(tr, "transferOwnership");
            assertTrue(td.findElement(By.tagName("button")).isEnabled());

            TransferCCOwnershipDialog transferCCOwnershipDialog =
                    viewEditCoreComponentPage.openTransferCCOwnershipDialog(tr);
            assertThrows(NoSuchElementException.class, () -> transferCCOwnershipDialog.transfer(endUser.getLoginId()));
        }
    }

    @Test
    public void test_TA_10_19_4() {
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
        thisAccountWillBeDeletedAfterTests(developer);

        String branch = "Working";
        ReleaseObject release = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber(branch);
        NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI("http://www.openapplications.org/oagis/10");

        CoreComponentAPI coreComponentAPI = getAPIFactory().getCoreComponentAPI();
        ACCObject randomACC1 = coreComponentAPI.createRandomACC(developer, release, namespace, "WIP");
        ACCObject randomACC2 = coreComponentAPI.createRandomACC(developer, release, namespace, "WIP");

        DTObject dataType = coreComponentAPI.getBDTByGuidAndReleaseNum("dd0c8f86b160428da3a82d2866a5b48d", release.getReleaseNumber());
        BCCPObject randomBCCP = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "WIP");
        coreComponentAPI.appendBCC(randomACC1, randomBCCP, "WIP");
        coreComponentAPI.appendBCC(randomACC2, randomBCCP, "WIP");

        HomePage homePage = loginPage().signIn(developer.getLoginId(), developer.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage =
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        BCCPViewEditPage bccpViewEditPage = viewEditCoreComponentPage.openBCCPViewEditPageByDenAndBranch(randomBCCP.getDen(), branch);
        BCCPViewEditPage.BCCPPanel bccpPanel = bccpViewEditPage.getBCCPPanelContainer().getBCCPPanel();
        String randomPropertyTerm = randomAlphabetic(5, 10).replaceAll(" ", "");
        randomPropertyTerm = Character.toUpperCase(randomPropertyTerm.charAt(0)) + randomPropertyTerm.substring(1).toLowerCase();
        randomPropertyTerm = "Test Object " + randomPropertyTerm;
        bccpPanel.setPropertyTerm(randomPropertyTerm);
        bccpViewEditPage.hitUpdateButton();

        {
            viewEditCoreComponentPage.openPage();
            ACCViewEditPage accViewEditPage = viewEditCoreComponentPage.openACCViewEditPageByDenAndBranch(randomACC1.getDen(), branch);
            WebElement bccNode = accViewEditPage.getNodeByPath("/" + randomACC1.getDen() + "/" + randomPropertyTerm);
            ACCViewEditPage.BCCPanelContainer bccPanelContainer = accViewEditPage.getBCCPanelContainer(bccNode);
            assertEquals(randomPropertyTerm, getText(bccPanelContainer.getBCCPPanel().getPropertyTermField()));
        }

        {
            viewEditCoreComponentPage.openPage();
            ACCViewEditPage accViewEditPage = viewEditCoreComponentPage.openACCViewEditPageByDenAndBranch(randomACC2.getDen(), branch);
            WebElement bccNode = accViewEditPage.getNodeByPath("/" + randomACC2.getDen() + "/" + randomPropertyTerm);
            ACCViewEditPage.BCCPanelContainer bccPanelContainer = accViewEditPage.getBCCPanelContainer(bccNode);
            assertEquals(randomPropertyTerm, getText(bccPanelContainer.getBCCPPanel().getPropertyTermField()));
        }
    }
}

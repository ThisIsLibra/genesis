/*
 * Copyright (C) 2019 Max 'Libra' Kersten [@LibraAnalysis]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model.mitre;

import java.util.ArrayList;
import java.util.List;

/**
 * Within this class, all MITRE ATT&CK tactics are defined based upon
 * techniques. Each tactic contains a list of techniques, as well as the name of
 * the tactic.
 *
 * The MITRE ATT&CK Matrices from April 2019 is used in this class.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class TacticManager {

    /**
     * Gets the list of techniques that correspond with the
     * <code>Initial Access</code> tactic (see
     * https://attack.mitre.org/tactics/TA0001/)
     *
     * @return the techniques corresponding with the <code>Intial Access</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getInitialAccess() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.DriveByCompromise);
        techniques.add(Technique.ExternalRemoteServices);
        techniques.add(Technique.ExploitPublicFacingApplication);
        techniques.add(Technique.HardwareAdditions);
        techniques.add(Technique.ReplicationThroughRemovableMedia);
        techniques.add(Technique.SpearphishingAttachment);
        techniques.add(Technique.SpearphishingLink);
        techniques.add(Technique.SpearphishingViaService);
        techniques.add(Technique.SupplyChainCompromise);
        techniques.add(Technique.TrustedReleationship);
        techniques.add(Technique.ValidAccounts);

        String name = "InitialAccess";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Execution</code> tactic (see
     * https://attack.mitre.org/tactics/TA0002/)
     *
     * @return the techniques corresponding with the <code>Execution</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getExecution() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AppleScript);
        techniques.add(Technique.CMSTP);
        techniques.add(Technique.CommandLineInterface);
        techniques.add(Technique.CompiledHtmlFile);
        techniques.add(Technique.ControlPanelItems);
        techniques.add(Technique.DynamicDataExchange);
        techniques.add(Technique.ExecutionThroughApi);
        techniques.add(Technique.ExecutionThroughModuleLoad);
        techniques.add(Technique.ExploitationForClientExecution);
        techniques.add(Technique.GraphicalUserInterface);
        techniques.add(Technique.InstallUtil);
        techniques.add(Technique.LsassDriver);
        techniques.add(Technique.Launchctl);
        techniques.add(Technique.LocalJobScheduling);
        techniques.add(Technique.Mshta);
        techniques.add(Technique.Powershell);
        techniques.add(Technique.RegsvcsOrRegasm);
        techniques.add(Technique.Regsvr32);
        techniques.add(Technique.Rundll32);
        techniques.add(Technique.ScheduledTask);
        techniques.add(Technique.Scripting);
        techniques.add(Technique.ServiceExecution);
        techniques.add(Technique.SignedBinaryProxyExecution);
        techniques.add(Technique.Source);
        techniques.add(Technique.SpaceAfterFilename);
        techniques.add(Technique.ThirdPartSoftware);
        techniques.add(Technique.Trap);
        techniques.add(Technique.TrustedDeveloperUtilities);
        techniques.add(Technique.UserExecution);
        techniques.add(Technique.WindowsManagementInstrumentation);
        techniques.add(Technique.WindowsRemoteManagement);
        techniques.add(Technique.XlsScriptProcessing);

        String name = "Execution";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Persistance</code> tactic (see
     * https://attack.mitre.org/tactics/TA0003/)
     *
     * @return the techniques corresponding with the <code>Persistance</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getPersistence() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.BashProfile);
        techniques.add(Technique.Bashrc);
        techniques.add(Technique.AccessibilityFeatures);
        techniques.add(Technique.AccountManipulation);
        techniques.add(Technique.AppCertDlls);
        techniques.add(Technique.AppInitDlls);
        techniques.add(Technique.ApplicationShimming);
        techniques.add(Technique.AuthenticationPackage);
        techniques.add(Technique.BitsJobs);
        techniques.add(Technique.Bootkit);
        techniques.add(Technique.BrowserExtensions);
        techniques.add(Technique.ChangeDefaultFileAssociation);
        techniques.add(Technique.ComponentFirmware);
        techniques.add(Technique.ComponentObjectModelHijacking);
        techniques.add(Technique.CreateAccount);
        techniques.add(Technique.DllSearchOrderHijacking);
        techniques.add(Technique.DylibHijacking);
        techniques.add(Technique.Emond);
        techniques.add(Technique.ExternalRemoteServices);
        techniques.add(Technique.FileSystemPermissionsWeakness);
        techniques.add(Technique.HiddenFilesAndDirectories);
        techniques.add(Technique.Hooking);
        techniques.add(Technique.Hypervisor);
        techniques.add(Technique.ImageFileExecutionOptionsInjection);
        techniques.add(Technique.ImplantContainerImage);
        techniques.add(Technique.KernelModulesAndExtensions);
        techniques.add(Technique.LcLoadDylibAddition);
        techniques.add(Technique.LsassDriver);
        techniques.add(Technique.LaunchAgent);
        techniques.add(Technique.LaunchDaemon);
        techniques.add(Technique.Launchctl);
        techniques.add(Technique.LocalJobScheduling);
        techniques.add(Technique.LoginItem);
        techniques.add(Technique.LogonScripts);
        techniques.add(Technique.ModifyExistingService);
        techniques.add(Technique.NetshHelperDll);
        techniques.add(Technique.NewService);
        techniques.add(Technique.OfficeApplicationStartup);
        techniques.add(Technique.PathInterception);
        techniques.add(Technique.PlistModification);
        techniques.add(Technique.PortKnocking);
        techniques.add(Technique.PortMonitors);
        techniques.add(Technique.PowershellProfile);
        techniques.add(Technique.RcCommon);
        techniques.add(Technique.ReopenedApplications);
        techniques.add(Technique.RedundantAccess);
        techniques.add(Technique.RegistryRunKeys);
        techniques.add(Technique.StartupFolder);
        techniques.add(Technique.SipAndTrustProviderHijacking);
        techniques.add(Technique.ScheduledTask);
        techniques.add(Technique.Screensaver);
        techniques.add(Technique.SecuritySupportProvider);
        techniques.add(Technique.ServerSoftwareComponent);
        techniques.add(Technique.ServiceRegistryPermissionsWeakness);
        techniques.add(Technique.SetuidAndSetgid);
        techniques.add(Technique.ShortcutModification);
        techniques.add(Technique.StartupItems);
        techniques.add(Technique.SystemdService);
        techniques.add(Technique.SystemFirmware);
        techniques.add(Technique.TimeProviders);
        techniques.add(Technique.Trap);
        techniques.add(Technique.ValidAccounts);
        techniques.add(Technique.WebShell);
        techniques.add(Technique.WindowsManagementInstrumentationEventSubscription);
        techniques.add(Technique.WinlogonHelperDll);

        String name = "Persistance";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Privilege Escalation</code> tactic (see
     * https://attack.mitre.org/tactics/TA0004/)
     *
     * @return the techniques corresponding with the
     * <code>Privilege Escalation</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public static Tactic getPrivilegeEscalation() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AccessTokenManipulation);
        techniques.add(Technique.AccessibilityFeatures);
        techniques.add(Technique.AppCertDlls);
        techniques.add(Technique.AppInitDlls);
        techniques.add(Technique.ApplicationShimming);
        techniques.add(Technique.BypassUserAccountControl);
        techniques.add(Technique.DllSearchOrderHijacking);
        techniques.add(Technique.DylibHijacking);
        techniques.add(Technique.ElevatedExecutionWithPrompt);
        techniques.add(Technique.Emond);
        techniques.add(Technique.ExploitationForPrivilegeEscalation);
        techniques.add(Technique.ExtraWindowMemoryInjection);
        techniques.add(Technique.FileSystemPermissionsWeakness);
        techniques.add(Technique.Hooking);
        techniques.add(Technique.ImageFileExecutionOptionsInjection);
        techniques.add(Technique.LaunchDaemon);
        techniques.add(Technique.NewService);
        techniques.add(Technique.ParentPidSpoofing);
        techniques.add(Technique.PathInterception);
        techniques.add(Technique.PlistModification);
        techniques.add(Technique.PortMonitors);
        techniques.add(Technique.PowershellProfile);
        techniques.add(Technique.ProcessInjection);
        techniques.add(Technique.SidHistoryInjection);
        techniques.add(Technique.ScheduledTask);
        techniques.add(Technique.ServiceRegistryPermissionsWeakness);
        techniques.add(Technique.SetuidAndSetgid);
        techniques.add(Technique.StartupItems);
        techniques.add(Technique.SudoCaching);
        techniques.add(Technique.Sudo);
        techniques.add(Technique.ValidAccounts);
        techniques.add(Technique.WebShell);

        String name = "PrivilegeEscalation";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Defense Evasion</code> tactic (see
     * https://attack.mitre.org/tactics/TA0005/)
     *
     * @return the techniques corresponding with the
     * <code>Defense Evasion</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public static Tactic getDefenseEvasion() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.ApplicationAccessToken);
        techniques.add(Technique.AccessTokenManipulation);
        techniques.add(Technique.BitsJobs);
        techniques.add(Technique.BinaryPadding);
        techniques.add(Technique.BypassUserAccountControl);
        techniques.add(Technique.Cmstp);
        techniques.add(Technique.ClearCommandHistory);
        techniques.add(Technique.CodeSigning);
        techniques.add(Technique.CompiledHtmlFile);
        techniques.add(Technique.ComponentFirmware);
        techniques.add(Technique.ComponentObjectModelHijacking);
        techniques.add(Technique.ControlPanelItems);
        techniques.add(Technique.CompileAfterDelivery);
        techniques.add(Technique.DcShadow);
        techniques.add(Technique.DllSearchOrderHijacking);
        techniques.add(Technique.DllSideLoading);
        techniques.add(Technique.DeobfuscateOrDecodeFilesOrInformation);
        techniques.add(Technique.DisablingSecurityTools);
        techniques.add(Technique.ExploitationForDefenseEvasion);
        techniques.add(Technique.ExtraWindowMemoryInjection);
        techniques.add(Technique.ExecutionGuardrails);
        techniques.add(Technique.FileDeletion);
        techniques.add(Technique.FilePermissionsModification);
        techniques.add(Technique.FileSystemLogicalOffsets);
        techniques.add(Technique.GatekeeperBypass);
        techniques.add(Technique.GroupPolicyModification);
        techniques.add(Technique.Histcontrol);
        techniques.add(Technique.HiddenFilesAndDirectories);
        techniques.add(Technique.HiddenUsers);
        techniques.add(Technique.HiddenWindow);
        techniques.add(Technique.ImageFileExecutionOptionsInjection);
        techniques.add(Technique.IndicatorBlocking);
        techniques.add(Technique.IndicatorRemovalFromTools);
        techniques.add(Technique.IndicatorRemovalOnHost);
        techniques.add(Technique.IndirectCommandExecution);
        techniques.add(Technique.InstallRootCertificate);
        techniques.add(Technique.InstallUtil);
        techniques.add(Technique.LcMainHijacking);
        techniques.add(Technique.Launchctl);
        techniques.add(Technique.Masquerading);
        techniques.add(Technique.ModifyRegistry);
        techniques.add(Technique.Mshta);
        techniques.add(Technique.NtfsFileAttributes);
        techniques.add(Technique.NetworkShareConnectionRemoval);
        techniques.add(Technique.ObfuscatedFilesOrInformation);
        techniques.add(Technique.ParentPidSpoofing);
        techniques.add(Technique.PlistModification);
        techniques.add(Technique.PortKnocking);
        techniques.add(Technique.ProcessDoppelganging);
        techniques.add(Technique.ProcessHollowing);
        techniques.add(Technique.ProcessInjection);
        techniques.add(Technique.RedundantAccess);
        techniques.add(Technique.RegsvcsOrRegasm);
        techniques.add(Technique.Regsvr32);
        techniques.add(Technique.RevertCloudInstance);
        techniques.add(Technique.Rootkit);
        techniques.add(Technique.Rundll32);
        techniques.add(Technique.SipAndTrustProviderHijacking);
        techniques.add(Technique.Scripting);
        techniques.add(Technique.SignedBinaryProxyExecution);
        techniques.add(Technique.SignedScriptProxyExecution);
        techniques.add(Technique.SoftwarePacking);
        techniques.add(Technique.SpaceAfterFilename);
        techniques.add(Technique.TemplateInjection);
        techniques.add(Technique.Timestomp);
        techniques.add(Technique.TrustedDeveloperUtilities);
        techniques.add(Technique.UnusedOrUnsupportedCloudRegions);
        techniques.add(Technique.ValidAccounts);
        techniques.add(Technique.VirtualisationAndSandboxEvasion);
        techniques.add(Technique.WebService);
        techniques.add(Technique.WebSessionCookie);
        techniques.add(Technique.XslScriptProcessing);

        String name = "DefenseEvasion";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Credential Access</code> tactic (see
     * https://attack.mitre.org/tactics/TA0006/)
     *
     * @return the techniques corresponding with the
     * <code>Credential Access</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public static Tactic getCredentialAccess() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AccountManipulation);
        techniques.add(Technique.BashHistory);
        techniques.add(Technique.BruteForce);
        techniques.add(Technique.CloudInstanceMetadataApi);
        techniques.add(Technique.CredentialDumping);
        techniques.add(Technique.CredentialsFromWebBrowsers);
        techniques.add(Technique.CredentialsInFiles);
        techniques.add(Technique.CredentialsInRegistry);
        techniques.add(Technique.ExploitationForCredentialAccess);
        techniques.add(Technique.ForcedAutehntication);
        techniques.add(Technique.Hooking);
        techniques.add(Technique.InputCapture);
        techniques.add(Technique.InputPrompt);
        techniques.add(Technique.Kerberoasting);
        techniques.add(Technique.Keychain);
        techniques.add(Technique.LlmnrNbtnsPoisoning);
        techniques.add(Technique.NetworkSniffing);
        techniques.add(Technique.PasswordFilterDll);
        techniques.add(Technique.PrivateKeys);
        techniques.add(Technique.SecuritydMemory);
        techniques.add(Technique.StealApplicationAccessToken);
        techniques.add(Technique.StealWebSessionCookie);
        techniques.add(Technique.TwoFactorAuthenticationInterception);

        String name = "CredentialAccess";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Discovery</code> tactic (see
     * https://attack.mitre.org/tactics/TA0007/)
     *
     * @return the techniques corresponding with the <code>Discovery</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getDiscovery() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AccountDiscovery);
        techniques.add(Technique.ApplicationWindowDiscovey);
        techniques.add(Technique.BrowserBookmarkDiscovery);
        techniques.add(Technique.CloudServiceDashboard);
        techniques.add(Technique.CloudServiceDiscovery);
        techniques.add(Technique.DomainTrustDiscovery);
        techniques.add(Technique.FileAndDirectoryDiscovery);
        techniques.add(Technique.NetworkServiceScanning);
        techniques.add(Technique.NetworkShareDiscovery);
        techniques.add(Technique.NetworkSniffing);
        techniques.add(Technique.PasswordPolicyDiscovery);
        techniques.add(Technique.PeripheralDeviceDiscovery);
        techniques.add(Technique.PermissionGroupsDiscovery);
        techniques.add(Technique.ProcessDiscovery);
        techniques.add(Technique.QueryRegistry);
        techniques.add(Technique.RemoteSystemDiscovery);
        techniques.add(Technique.SecuritySoftwareDiscovery);
        techniques.add(Technique.SoftwareDiscovery);
        techniques.add(Technique.SystemInformationDiscovery);
        techniques.add(Technique.SystemNetworkConfigurationDiscovery);
        techniques.add(Technique.SystemNetworkConnectionsDiscovery);
        techniques.add(Technique.SystemOwnerOrUserDiscovery);
        techniques.add(Technique.SystemServiceDiscovery);
        techniques.add(Technique.SystemTimeDiscovery);
        techniques.add(Technique.VirtualisationAndSandboxEvasion);

        String name = "Discovery";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Lateral Movement</code> tactic (see
     * https://attack.mitre.org/tactics/TA0008/)
     *
     * @return the techniques corresponding with the
     * <code>Lateral Movement</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public static Tactic getLateralMovement() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AppleScript);
        techniques.add(Technique.ApplicationAccessToken);
        techniques.add(Technique.ApplicationDeploymentSoftware);
        techniques.add(Technique.DistributedComponentObjectModel);
        techniques.add(Technique.ExploitationOfRemoteServices);
        techniques.add(Technique.InternalSpearphishing);
        techniques.add(Technique.LogonScripts);
        techniques.add(Technique.PassTheHash);
        techniques.add(Technique.PassTheTicket);
        techniques.add(Technique.RemoteDesktopProtocol);
        techniques.add(Technique.RemoteFileCopy);
        techniques.add(Technique.RemoteServices);
        techniques.add(Technique.ReplicationThroughRemovableMedia);
        techniques.add(Technique.SshHijacking);
        techniques.add(Technique.SharedWebroot);
        techniques.add(Technique.TaintSharedContent);
        techniques.add(Technique.ThirdPartySoftware);
        techniques.add(Technique.WebSessionCookie);
        techniques.add(Technique.WindowsAdminShares);
        techniques.add(Technique.WindowsRemoteManagement);

        String name = "LateralMovement";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Collection</code> tactic (see
     * https://attack.mitre.org/tactics/TA0009/)
     *
     * @return the techniques corresponding with the <code>Collection</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getCollection() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AudioCapture);
        techniques.add(Technique.AutomatedCollection);
        techniques.add(Technique.ClipboardData);
        techniques.add(Technique.DataStaged);
        techniques.add(Technique.DataFromCloudStorageObject);
        techniques.add(Technique.DataFromInformationRepositories);
        techniques.add(Technique.DataFromLocalSystem);
        techniques.add(Technique.DataFromNetworkSharedDrive);
        techniques.add(Technique.DataFromRemovableMedia);
        techniques.add(Technique.EmailCollection);
        techniques.add(Technique.InputCapture);
        techniques.add(Technique.ManInTheBrowser);
        techniques.add(Technique.ScreenCapture);
        techniques.add(Technique.VideoCapture);

        String name = "Collection";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Exfiltration</code> tactic (see
     * https://attack.mitre.org/tactics/TA0010/)
     *
     * @return the techniques corresponding with the <code>Exfiltration</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getExfiltration() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AutomatedExfiltration);
        techniques.add(Technique.DataCompressed);
        techniques.add(Technique.DataEncrypted);
        techniques.add(Technique.DataTransferSizeLimits);
        techniques.add(Technique.ExfiltrationOverAlternativeProtocol);
        techniques.add(Technique.ExfiltrationOverCommandAndControlChannel);
        techniques.add(Technique.ExfiltrationOverOtherNetworkMedium);
        techniques.add(Technique.ExfiltrationOverPhysicalMedium);
        techniques.add(Technique.ScheduledTransfer);
        techniques.add(Technique.TransferDataToCloudAccount);

        String name = "Exfiltration";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Command & Control</code> tactic (see
     * https://attack.mitre.org/tactics/TA0011/)
     *
     * @return the techniques corresponding with the
     * <code>Command & Control</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public static Tactic getCommandAndControl() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.CommonlyUsedPort);
        techniques.add(Technique.CommunicationThroughRemovableMedia);
        techniques.add(Technique.ConnetionProxy);
        techniques.add(Technique.CustomCommandAndControlProtocol);
        techniques.add(Technique.CustomCryptographicProtocol);
        techniques.add(Technique.DataEncoding);
        techniques.add(Technique.DataObfuscation);
        techniques.add(Technique.DomainFronting);
        techniques.add(Technique.DomainGenerationAlgorithems);
        techniques.add(Technique.FallbackChannels);
        techniques.add(Technique.MultiStageChannels);
        techniques.add(Technique.MultiHopProxy);
        techniques.add(Technique.MultibandCommunication);
        techniques.add(Technique.MultilayerEncryption);
        techniques.add(Technique.PortKnocking);
        techniques.add(Technique.RemoteAccessTools);
        techniques.add(Technique.RemoteFileCopy);
        techniques.add(Technique.StandardApplicationLayerProtocol);
        techniques.add(Technique.StandardCryptographicProtocol);
        techniques.add(Technique.StandardNonApplicationLayerProtocol);
        techniques.add(Technique.UncommonlyUsedPort);
        techniques.add(Technique.WebService);

        String name = "CommandAndControl";
        return new Tactic(name, techniques);
    }

    /**
     * Gets the list of techniques that correspond with the <code>Impact</code>
     * tactic (see https://attack.mitre.org/tactics/TA0040/)
     *
     * @return the techniques corresponding with the <code>Impact</code> tactic
     * and the name of the tactic in a <code>Tactic</code> object.
     */
    public static Tactic getImpact() {
        List<Technique> techniques = new ArrayList<>();
        techniques.add(Technique.AccountAccessRemoval);
        techniques.add(Technique.DataDestruction);
        techniques.add(Technique.DataEncryptedForImpact);
        techniques.add(Technique.Defacement);
        techniques.add(Technique.DiskContentWipe);
        techniques.add(Technique.DiskStructureWipe);
        techniques.add(Technique.EndpointDenialOfService);
        techniques.add(Technique.FirmwareCorruption);
        techniques.add(Technique.InhibitSystemRecovery);
        techniques.add(Technique.NetworkDenialOfService);
        techniques.add(Technique.ResourceHijacking);
        techniques.add(Technique.RuntimeDataManipulation);
        techniques.add(Technique.ServiceStop);
        techniques.add(Technique.StoredDataManipulation);
        techniques.add(Technique.SystemShutdownOrReboot);
        techniques.add(Technique.TransmittedDataManipulation);

        String name = "Impact";
        return new Tactic(name, techniques);
    }
}

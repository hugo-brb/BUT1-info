<Workspace>
	<ModuleRelationships>
		<Module ModuleID="0" SystemClassName="SegaMegaDrive" ModuleClassName="SegaMegaDrive" ModuleInstanceName="MD1600">
			<ExportConnector ConnectorClassName="ControlPort" ConnectorInstanceName="Control Port 1" />
			<ExportConnector ConnectorClassName="ControlPort" ConnectorInstanceName="Control Port 2" />
			<ExportConnector ConnectorClassName="ControlPort" ConnectorInstanceName="Control Port 3" />
			<ExportConnector ConnectorClassName="CartridgePort" ConnectorInstanceName="Cartridge Port" />
			<ExportConnector ConnectorClassName="ExpansionPort" ConnectorInstanceName="Expansion Port" />
		</Module>
		<Module ModuleID="1" SystemClassName="SegaMegaDrive" ModuleClassName="Control6" ModuleInstanceName="Generic 6-Button Controller">
			<ImportConnector ExportingModuleID="0" ConnectorClassName="ControlPort" ConnectorInstanceNameLocal="Control Port" ConnectorInstanceNameRemote="Control Port 1" />
		</Module>
		<Module ModuleID="2" SystemClassName="SegaMegaDrive" ModuleClassName="ROMLoader" ModuleInstanceName="Generic ROM Loader" />
	</ModuleRelationships>
	<ViewLayout>
		<MainWindowState SizeX="1142" SizeY="603" Maximized="0">
			<MainDockedWindow ActiveHostedWindowID="1">
				<HostedContentWindows>
					<HostedWindow WindowID="1" ViewType="Dockable" WindowTitle="VDP - Image Window" />
				</HostedContentWindows>
				<HostedDockingWindows>
					<DockingWindowFrame DockPos="Right" AutoHide="0" DesiredWidth="827" DesiredHeight="315" ActiveHostedWindowID="2">
						<HostedContentWindows>
							<HostedWindow WindowID="2" ViewType="Dockable" WindowTitle="Main 68000 - Registers" />
						</HostedContentWindows>
						<HostedDockingWindows>
							<DockingWindowFrame DockPos="Right" AutoHide="0" DesiredWidth="573" DesiredHeight="315" ActiveHostedWindowID="3">
								<HostedContentWindows>
									<HostedWindow WindowID="3" ViewType="Dockable" WindowTitle="Main 68000 - Disassembly" />
								</HostedContentWindows>
							</DockingWindowFrame>
						</HostedDockingWindows>
					</DockingWindowFrame>
				</HostedDockingWindows>
			</MainDockedWindow>
			<FloatingWindows />
		</MainWindowState>
		<ViewPresenterState ViewPresenterGroupName="GenericAccessDebugMenu" ViewPresenterName="Registers" Target="Device" DeviceInstanceName="Main 68000" ModuleID="0">
			<ViewState>
				<WindowState WindowID="2" />
			</ViewState>
		</ViewPresenterState>
		<ViewPresenterState ViewPresenterGroupName="VDPDebugMenu" ViewPresenterName="Image" Target="Device" DeviceInstanceName="VDP" ModuleID="0">
			<ViewState>
				<WindowState WindowID="1" />
			</ViewState>
		</ViewPresenterState>
		<ViewPresenterState ViewPresenterGroupName="ProcessorDebugMenu" ViewPresenterName="Disassembly" Target="Device" DeviceInstanceName="Main 68000" ModuleID="0">
			<ViewState>
				<WindowState WindowID="3" />
			</ViewState>
		</ViewPresenterState>
	</ViewLayout>
</Workspace>

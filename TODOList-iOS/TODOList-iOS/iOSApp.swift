import SwiftUI
import data

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
	var body: some Scene {
		WindowGroup {
            ContentView().environmentObject(UserManager.shared)
		}
	}
}

class KoinManager {
    static let helper = TodoHelper()
    static let commonHelper = CommonHelper()
}

import SwiftUI
import data

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            ContentView().environmentObject(UserManager.shared)
		}
	}
}

class KoinManager {
    static let helper = TodoHelper()
}

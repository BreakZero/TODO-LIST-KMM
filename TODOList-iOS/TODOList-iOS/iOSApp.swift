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

final class KoinManager {
    static let userRepository = UserRepository()
    static let taskRepository = TaskRepository()
    static let commonHelper = CommonHelper()
}

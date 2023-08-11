import SwiftUI
import data

struct ContentView: View {
    @EnvironmentObject var userManager: UserManager

	var body: some View {
        if userManager.loggedUser != nil {
            TodoListScreen()
        } else {
            SignUpScreen()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

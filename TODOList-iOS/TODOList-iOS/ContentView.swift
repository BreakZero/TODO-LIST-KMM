import SwiftUI
import data

struct ContentView: View {
    @EnvironmentObject var userManager: UserManager

	var body: some View {
        if userManager.loggedUser != nil {
            Text("has Logged User: \(userManager.loggedUser?.fullName ?? "")")
        } else {
            VStack {
                Text("Not User")
                Button(action: {
                    userManager.insertUser(user: ModelUser(uid: "123", fullName: "Dougie008", email: "527916577@qq.com", createAt: 12))
                }, label: {
                    Text("Button")
                })
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

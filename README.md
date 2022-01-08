# Petty Planet

Petty planet is an place where you can upload pics of abandoned pets so that the people interested to adopt pets can connect adopt the pets they wish to adopt.

* Used Firebase to storage to store the Images uploaded by the users and then waited for the imageurl to come , after the imageurl is successfully generated , I used the imageurl along with the other information provided by the user to create a post in the Firebase Realtime Database
* Implemented Standard Google Login to authenticate users of my app
* Integrated Camera and Gallery acess in the app so that users can users can upload images using any medium they prefer
* The app uses many fragments and it consumes the jetpack navigation components to navigate around different fragments
* Used Firebase to store the user details online and used SharedPreferences to store the user information in the device itself

* Used Room To store the posts user desires in the device itself

* Used dependency injection to inject  the required information at the desired places (for eg Injecting the instance of Room Db in different ViewModel)

* Used ViewBinding and followed MVVM to make the data persistent even if the views are recreated
* Used Lottie Animation to add some animations
* Wrote tests to check that the Room Db is working Fine(used the truth library by google for making assertions)





## Technologies Used
[Kotlin](https://choosealicense.com/licenses/mit/)

[Firebase Storage](https://firebase.google.com/products/storage)

[Firebase Realtime Database](https://firebase.google.com/docs/database)

[Room](https://developer.android.com/jetpack/androidx/releases/room)

[Dependency Injection](https://developer.android.com/training/dependency-injection)

[Coroutines](https://developer.android.com/kotlin/coroutines)

[ViewBinding](https://developer.android.com/topic/libraries/view-binding)

[MVVM](https://developer.android.com/jetpack/guide)

[Lottie Animations](https://github.com/airbnb/lottie-android)

[Motion Toast](https://github.com/Spikeysanju/MotionToast)

[Truth](https://truth.dev/)


<table>
  <tr>
    <td>Login Screen</td>
     <td>Main Feed</td>
     <td>Upload Fragment</td>
  </tr>
  <tr>
    <td><img src ="https://user-images.githubusercontent.com/75121767/148033004-f4537cfa-543a-4bf8-9029-36f4bf583287.jpeg" width=270 height=480></td>
    <td><img src= "https://user-images.githubusercontent.com/75121767/148658029-aef670bd-34b0-4630-b489-dd5318e28054.jpeg" width=270 height=480></td>
    <td><img src="https://user-images.githubusercontent.com/75121767/148658057-17fc8a03-d3f8-4a85-a93d-3e896a5b0427.jpeg" width=270 height=480></td>
  </tr>
  <tr>
    <td><img src ="https://user-images.githubusercontent.com/75121767/148658082-d49f7673-3f44-4674-b276-ab3b3e053ffe.jpeg" width=270 height=480></td>
    <td><img src= "https://user-images.githubusercontent.com/75121767/148658085-215e2d93-8563-45b5-8e99-f8325d803b0f.jpeg" width=270 height=480></td>
    <td><img src="https://user-images.githubusercontent.com/75121767/148658092-36785388-7398-4c7c-880a-a4dc8f8fe643.jpeg" width=270 height=480></td>
  </tr>

 </table>


## Todo/Future Of The Project
* For now I will be focusing to make changes to the UI as the current UI is not attractive at all
 * The upload fragment can be improved in many ways , I can compress the image the before uploading to make the upload and retrieval faster , also the whole upload process can be done in an background service
* Also I wish to cache the posts using Room Db so that while the new posts are still being fetched the users has something to interact with 
* Write more tests
* Implement other login methods such as Facebook Login


## License
[MIT](https://choosealicense.com/licenses/mit/)


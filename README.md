# Petty Planet

Petty planet is an place where you can upload pics of abandoned pets so that the people interested to adopt pets can connect adopt the pets they wish to adopt.

* Used Firebase to storage to store the Images uploaded by the users and then waited for the imageurl to come , after the imageurl is successfully generated , I used the imageurl along with the other information provided by the user to create a post in the Firebase Realtime Database
* Implemented Standard Google Login to authenticate users of my app
* Integrated Camera and Gallery acess in the app so that users can users can upload images using any medium they prefer
* The app uses many fragments and it consumes the jetpack navigation components to navigate around different fragments
* Used Firebase to store the user details online and used SharedPreferences to store the user information in the device itself
* Used ViewBinding and followed MVVM to make the data persistent even if the views are recreated





## Technologies Used
[Kotlin](https://choosealicense.com/licenses/mit/)

[Firebase Storage](https://firebase.google.com/products/storage)

[Firebase Realtime Database](https://firebase.google.com/docs/database)

[Coroutines](https://developer.android.com/kotlin/coroutines)

[ViewBinding](https://developer.android.com/topic/libraries/view-binding)

[MVVM](https://developer.android.com/jetpack/guide)


<table>
  <tr>
    <td>Login Screen</td>
     <td>Main Feed</td>
     <td>Upload Fragment</td>
  </tr>
  <tr>
    <td><img src ="https://user-images.githubusercontent.com/75121767/148033004-f4537cfa-543a-4bf8-9029-36f4bf583287.jpeg" width=270 height=480></td>
    <td><img src="https://user-images.githubusercontent.com/75121767/148033170-8acfe579-ef22-4968-bb5b-d627bef68e93.jpeg" width=270 height=480></td>
    <td><img src="https://user-images.githubusercontent.com/75121767/148033292-e4d3aa2c-ba5d-496c-b2d7-10a6f25172c9.jpeg" width=270 height=480></td>
  </tr>

 </table>


## Todo/Future Of The Project
* For now I will be focusing to make changes to the UI as the current UI is not attractive at all
 * The upload fragment needs a lot of work to be done , the whole upload process can be done asynchronously without locking the user at upload fragment while the upload isn't complete 
* Also I wish to cache the posts using Room Db so that while the new posts are still being fetched the users has something to interact with 

## License
[MIT](https://choosealicense.com/licenses/mit/)


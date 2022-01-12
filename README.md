a. Use Singleton pattern, Dependency injection pattern. 

b. Use MVVM, Coroutine, Retrofit.

- Model: It represents the data and the business logic of the Android Application. It consists of the business logic - local and remote data source, model classes, repository.

- View: It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.

- ViewModel: It is a bridge between the View and Model(business logic). It does not have any clue which View has to use it as it does not have a direct reference to the View. So basically, the ViewModel should not be aware of the view who is interacting with. It interacts with the Model and exposes the observable that can be observed by the View.

Handle Retrofit with Coroutines:

- With async we create new coroutine and returns its future result as an implementation of [Deferred].
The coroutine builder called launch allow us to start a coroutine in background and keep working in the meantime.
so async will run in background then return its promised result to parent coroutine which created by launch.
when we get a result, it is up to us to do handle the result.

My package in the project will look like below:
+ adapter folder
+ api folder
+ models folder
+ ui folder

c. Clone from git and run. 

d. 1,2,3,4,6a, and 8.

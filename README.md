# Clean Architecture for Android

I commenced with simple VIPER architecture and tried to solve different problems by focussing on how the make presenter testable using only local unit test-cases.

As per VIPER with some customization, we have following components -
1. **View** : Activity, Fragments, Dialog Fragments etc.
2. **Presenter** : Presenter will contain all view logic and generate a view-model and provide it to the view. With any action on view, view-model can be updated by presenter.
3. **View-Model** : It is a POJO which represents our view.
4. **Interactor** : It will take care of different data sources like database, network etc. It will also contain business logic. It will provide data like location, sensors etc. from android system-services. All the data will be provided in the form of an entity.
5. **Entity** : It is a POJO for our data.
6. **Navigator** : It will contain methods like finish, showToast, startActivity, showProgressDialog etc. This class will be injected in presenter for routing and android related events. This will be mocked at the time of testing presenter.
7. **Prefs and Resource Provider** : Prefs is a wrapper class for preferences whereas ResourceProvider is a wrapper class for resources. These classes can be injected in presenter as per the requirement and can be mocked at the time of testing presenter.
<br />
<br />

![picture alt](images/Flow%20Diagram.png)

<br />

For more detail go to [this](https://medium.com/@chirag.jain5000/unit-testing-in-android-is-a-mess-80694ebbde07) article.

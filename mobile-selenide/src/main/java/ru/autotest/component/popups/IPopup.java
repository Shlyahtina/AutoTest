package ru.autotest.component.popups;

public interface IPopup<T> {

    T popupShouldVisible();
    T popupShouldNOTVisible();
}

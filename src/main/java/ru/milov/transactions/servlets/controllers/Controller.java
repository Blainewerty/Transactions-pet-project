package ru.milov.transactions.servlets.controllers;

public interface Controller<REQ, RES> {
    RES execute(REQ request);

    Class<REQ> getRequestClass();
}

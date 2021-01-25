package ru.milov.transactions.servlets;

public interface Controller<REQ, RES> {
    RES execute(REQ request);
}

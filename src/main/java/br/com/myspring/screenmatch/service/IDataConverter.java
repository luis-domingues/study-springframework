package br.com.myspring.screenmatch.service;

public interface IDataConverter {
    <T> T getData (String json, Class<T> tClass);
}

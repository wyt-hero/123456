package com.xinhu.wealth.jgt.util;

import com.alibaba.fastjson.JSON;
import com.xinhu.wealth.jgt.constants.ResultEntity;

import java.io.Serializable;

/** * 统一API响应结果封装 * 新增对应枚举值的封装 */
public class Result<T> implements Serializable {
	/** * 返回的code */
	private int code;
	/** * 返回的文言 */
	private String message;
	/** * 返回的数值 */
	private T data;

	public int getCode() {
		return code;
	}

	/** * 设置code 单个值设置 逐步废弃 * @param resultCode * @return */
	public Result<T> setCode(int resultCode) {
		this.code = resultCode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	/** * 针对单个值设置 * @param message 响应消息 * @return */
	public Result<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		if(data == null){
			return (T) new Object();
		}
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}


	/** * 封装JSON 转换 * @return */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * 默认成功结构体 code ：SUCCESS
	 *
	 * @return
	 */
	public static <T> Result<T> success() {
		return new Result<T>().setCode(ResultEntity.SUCCESS.getCode()).setMessage(ResultEntity.SUCCESS.getMsg());
	}

	/**
	 * 带 数据 code ：SUCCESS
	 *
	 * @return
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>().setCode(ResultEntity.SUCCESS.getCode()).setMessage(ResultEntity.SUCCESS.getMsg())
				.setData(data);
	}

	/**
	 * 自定义成功返回
	 *
	 * @return
	 */
	public static <T> Result<T> success(ResultEntity resultEntity) {
		return new Result<T>().setCode(resultEntity.getCode()).setMessage(resultEntity.getMsg());
	}

	/**
	 * 默认失败返回 code ：FAILURE
	 *
	 * @return
	 */
	public static <T> Result<T> failure() {
		return new Result<T>().setCode(ResultEntity.FAIL.getCode()).setMessage(ResultEntity.FAIL.getMsg());
	}

	/**
	 * 自定义失败返回
	 *
	 * @return
	 */
	public static <T> Result<T> failure(ResultEntity resultEntity) {
		return new Result<T>().setCode(resultEntity.getCode()).setMessage(resultEntity.getMsg());
	}



	/**
	 * 自定义code和message
	 * @return
	 */
	public static <T> Result<T> genResult(int code, String message){
		return new Result<T>()
				.setCode(code)
				.setMessage(message);
	}

	/**
	 * 自定义code和message
	 * @return
	 */
	public static <T> Result<T> genResult(ResultEntity resultEntity){
		return new Result<T>()
				.setCode(resultEntity.getCode()).setMessage(resultEntity.getMsg());
	}
}

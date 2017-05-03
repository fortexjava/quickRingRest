/**
 * <p>
 * Description</p>
 *
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

/**
 * @author Administrator
 *
 */
public class RestObj
{

	/**
	 *
	 */
	private String error = "";
	private Object data;

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

}

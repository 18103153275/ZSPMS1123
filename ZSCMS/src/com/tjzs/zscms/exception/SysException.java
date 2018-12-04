package com.tjzs.zscms.exception;
public class SysException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4846497347166289724L;
		//异常码
		private int errCode;
		//异常信息
		private String errMsg;
		
		public  SysException(int errCode, String errMsg) {
			
			this.errCode = errCode;
			this.errMsg = errMsg;
		}
		/**
		 * @return errCode
		 */
		public int getErrCode() {
			return errCode;
		}
		/**
		 * @param errCode 要设置的 errCode
		 */
		public void setErrCode(int errCode) {
			this.errCode = errCode;
		}
		/**
		 * @return errMsg
		 */
		public String getErrMsg() {
			return errMsg;
		}
		/**
		 * @param errMsg 要设置的 errMsg
		 */
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
		
		
}

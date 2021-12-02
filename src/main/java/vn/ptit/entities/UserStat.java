package vn.ptit.entities;

import java.math.BigDecimal;

public class UserStat extends UserInfo{
	private BigDecimal tongTienMua;

	public BigDecimal getTongTienMua() {
		return tongTienMua;
	}

	public void setTongTienMua(BigDecimal tongTienMua) {
		this.tongTienMua = tongTienMua;
	}
	
}

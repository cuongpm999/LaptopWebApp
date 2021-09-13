package vn.ptit.beans;

import com.github.slugify.Slugify;

public class Utilities {
	public String createSeoLink(final String text) {
		Slugify slg = new Slugify();
		String result = slg.slugify(text + "-" + (System.currentTimeMillis()));
		return result;
	}

}

/*
 * Copyright (C) 2016 thislooksfun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tlf.blanklines;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.modules.editor.indent.api.Indent;
import org.netbeans.spi.editor.document.OnSaveTask;

import static com.tlf.util.TLFUtil.println;

/** @author thislooksfun */
public class FormatBlankLines implements OnSaveTask
{
	private static final long serialVersionUID = 1L;
	private final Document document;

	public FormatBlankLines(Document document)
	{
		this.document = document;
		println("Well, things are happening...");
	}

	@Override
	public void performTask()
	{
		try {
			Indent indent = Indent.get(document);
			indent.lock();
			indent.reindent(0, document.getLength());
			indent.unlock();
		} catch (BadLocationException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void runLocked(Runnable r)
	{
		// I honestly don't know why this is needed,
		// but for some reason it is.
		r.run();
	}

	@Override
	public boolean cancel()
	{
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}

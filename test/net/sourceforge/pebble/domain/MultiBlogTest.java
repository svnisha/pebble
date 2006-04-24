/*
 * Copyright (c) 2003-2006, Simon Brown
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 *   - Neither the name of Pebble nor the names of its contributors may
 *     be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sourceforge.pebble.domain;

import java.util.Date;

/**
 * Tests for the MultiBlog class.
 *
 * @author    Simon Brown
 */
public class MultiBlogTest extends MultiBlogTestCase {

  private MultiBlog blog;

  public void setUp() {
    super.setUp();

    this.blog = BlogManager.getInstance().getMultiBlog();
  }

  /**
   * Tests that the date of the most recent blog entry can be calculated.
   */
  public void testLastModified() {
    assertEquals(new Date(0), blog.getLastModified());

    BlogEntry blogEntry = blog1.getBlogForToday().createBlogEntry();
    blog1.getBlogForToday().addEntry(blogEntry);
    assertEquals(blogEntry.getDate(), blog.getLastModified());
  }

  /**
   * Tests that the recent blog entries can be accessed.
   */
  public void testRecentBlogEntries() {
    // should be no daily blogs to start with
    assertTrue(blog.getRecentBlogEntries(0, true).isEmpty());

    // now add an entry
    BlogEntry blogEntry = blog1.getBlogForToday().createBlogEntry();
    blog1.getBlogForToday().addEntry(blogEntry);

    // ask for zero entries
    assertTrue(blog.getRecentBlogEntries(0, true).isEmpty());

    // ask for 1 entry
    assertTrue(blog.getRecentBlogEntries(1, true).contains(blogEntry));
  }

}

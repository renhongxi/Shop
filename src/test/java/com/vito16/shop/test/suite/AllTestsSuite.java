/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * muyu@yiji.com 2015-10-30 15:59 创建
 */
package com.vito16.shop.test.suite;

import com.vito16.shop.test.AdminTest;
import com.vito16.shop.test.OrderTest;
import com.vito16.shop.test.UserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AdminTest.class, OrderTest.class, UserTest.class})
public class AllTestsSuite {

}

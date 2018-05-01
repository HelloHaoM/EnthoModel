/**
 * A class that is used to simulate the model
 * @author haomai
 *
 */
public class Controller {
	/**
	 * 按我的理解来说，controller应该要有一个tick函数，函数里面有4个流程：
	 * 1. immigrate (产生一个agent并占据一个空block,strategy, color之类的要随机确定)
	 * 2. interact	(调用agent间interact)
	 * 3. reproduce (随机调用agent的reproduce)
	 * 4. die (随机让某个agents死掉)
	 */
	
	/**
	 * 维护一个agent list 代表现在的agent
	 * 因为要输出最终结果，可能还要维护一下4种agent的数目
	 */
	
	/**
	 * 在MainSimulator函数里，循环调用tick函数多次，然后输出csv即可
	 */

}

# 146. LRU 缓存

# https://leetcode.cn/problems/lru-cache/description/

# 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
# LRU即最久未使用算法

# 实现 LRUCache 类：
# LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
# int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
# void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
# 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。


# 示例：

# 输入
# ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
# [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
# 输出
# [null, null, null, 1, null, -1, null, -1, 3, 4]

# 解释
# LRUCache lRUCache = new LRUCache(2);
# lRUCache.put(1, 1); // 缓存是 {1=1}
# lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
# lRUCache.get(1);    // 返回 1
# lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
# lRUCache.get(2);    // 返回 -1 (未找到)
# lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
# lRUCache.get(1);    // 返回 -1 (未找到)
# lRUCache.get(3);    // 返回 3
# lRUCache.get(4);    // 返回 4
 
# 提示：

# 1 <= capacity <= 3000
# 0 <= key <= 10000
# 0 <= value <= 105
# 最多调用 2 * 105 次 get 和 put
import collections

class LRUCache:
    '''
    使用双向链表记录使用顺序
    使用HASH表存储键值对

    '''
    class DLinkNode:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None
            

    def __init__(self, capacity: int):
        self.capacity = capacity
        # hash表
        self.cache = dict()
        self.head = self.DLinkNode(-1, 0)
        self.tail = self.DLinkNode(-1, 0)

        self.head.next = self.tail
        self.tail.prev = self.head

        self.size = 0

    def get(self, key: int) -> int:
        if key in self.cache:
            node = self.cache[key]
            self.addTOHead(node)
            return node.value
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if self.capacity == 0:
            return
        
        if key in self.cache:
            node = self.cache[key]
            node.value = value
            self.addTOHead(node)
            return
        
        if self.size == self.capacity:
            prev = self.tail.prev
            self.tail.prev = prev.prev
            prev.prev.next = self.tail
            # 从字典中删除
            self.cache.pop(prev.key)
            # 注意大小需要减1
            self.size -= 1
        
        node = self.DLinkNode(key, value)
        # 插入节点必须动四个指针
        node.prev = self.head
        node.next = self.head.next
        node.next.prev = node
        self.head.next = node

        self.cache[key] = node
        # 只有添加时才加1
        self.size += 1
            


    def addTOHead(self, node: DLinkNode):
        # 删除节点动两个指针
        node.prev.next = node.next
        node.next.prev = node.prev

        # 插入节点动四个指针
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    


class LRUCache1(collections.OrderedDict):

    '''
    使用封装好的数据结构
    '''
    def __init__(self, capacity: int):
        super().__init__()
        self.capacity = capacity


    def get(self, key: int) -> int:
        if key not in self:
            return -1
        self.move_to_end(key)
        return self[key]

    def put(self, key: int, value: int) -> None:
        if key in self:
            self.move_to_end(key)
        self[key] = value
        if len(self) > self.capacity:
            self.popitem(last=False)

if __name__  == "__main__":
    s = LRUCache(2)
    s.put(1, 1)
    s.put(2, 2)
    print(s.get(1))
    s.put(3,3)
    print(s.get(2))
    s.put(4, 4)
    print(s.get(1))
    print(s.get(3))
    print(s.get(4))
class TreeNode:
    def __init__(self, value: int=None) -> None:
        self.value = value
        self.left: TreeNode = None
        self.right: TreeNode = None

    def insert(self, value: int) -> None:
        if value < self.value:
            if self.left is None:
                self.left = TreeNode(value)
            else:
                self.left.insert(value)
        elif value > self.value:
            if self.right is None:
                self.right = TreeNode(value)
            else:
                self.right.insert(value)
    def insert_b(self, value: int) -> None:
        p = self
        while p is not None:
            if value < p.value:
                if p.left is None:
                    p.left = TreeNode(value)
                    break
                else:
                    p = p.left
            elif value > p.value:
                if p.right is None:
                    p.right = TreeNode(value)
                    break
                else:
                    p = p.right
            

    def insert_list(self, values: list) -> None:
        for value in values:
            self.insert(value)

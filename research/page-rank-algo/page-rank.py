import numpy as np

# vid reference - https://www.youtube.com/watch?v=RVIr8Y5iseks
# Notes: ./research/page-rank-algo/page-rank-notes.pdf
# Papar: ./research/page-rank-algo/pagerank.pdf

def create_transition_matrix(links, num_nodes):
    """
    Creates the transition matrix for the PageRank algorithm.
    :param links: List of tuples representing directed links between nodes (from, to).
    :param num_nodes: Total number of nodes in the graph.
    :return: Transition matrix (stochastic matrix).
    """
    matrix = np.zeros((num_nodes, num_nodes))
    
    for from_node, to_node in links:
        matrix[to_node, from_node] += 1
    
    # Normalize columns to create a stochastic matrix
    for i in range(num_nodes):
        column_sum = np.sum(matrix[:, i])
        if column_sum != 0:
            matrix[:, i] /= column_sum
        else:
            matrix[:, i] = 1.0 / num_nodes  # Handling dangling nodes (no outbound links)
    
    return matrix

def page_rank(links, num_nodes, damping=0.85, max_iterations=100, tol=1.0e-6):
    """
    Computes the PageRank of each node in the graph.
    :param links: List of tuples representing directed links between nodes (from, to).
    :param num_nodes: Total number of nodes in the graph.
    :param damping: Damping factor (usually set to 0.85).
    :param max_iterations: Maximum number of iterations to run the algorithm.
    :param tol: Tolerance for convergence.
    :return: PageRank vector.
    """
    # Create the transition matrix
    transition_matrix = create_transition_matrix(links, num_nodes)
    
    # Initialize PageRank vector with equal probability to each node
    pagerank = np.ones(num_nodes) / num_nodes
    
    # Initialize the teleportation vector - personalization vector
    teleport = np.ones(num_nodes) / num_nodes
    
    for iteration in range(max_iterations):
        new_pagerank = damping * np.dot(transition_matrix, pagerank) + (1 - damping) * teleport
        
        # Check for convergence
        if np.linalg.norm(new_pagerank - pagerank, ord=1) < tol:
            print(f'Converged after {iteration + 1} iterations')
            return new_pagerank
        
        pagerank = new_pagerank
    
    print(f'Max iterations reached: {max_iterations}')
    return pagerank

# Example usage
links = [(0, 1), (1, 2), (2, 0), (0, 2), (2, 1)]
num_nodes = 3
pagerank_values = page_rank(links, num_nodes)
print("PageRank values:", pagerank_values)

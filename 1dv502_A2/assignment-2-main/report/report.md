# Reflection on my Intended Design
Introduction
in the reflection, we compare the initial design from assignment_2 with the refined design in assignment_3, evaluating how well the intended design aligned with the final working implementation. We discuss missing or extra classes, naming conventions, relationships, object conections, complexity distribution, and the evolution of the diagrams. At the end we asses our intial design's effectiveness and the key lessons learned from designing before implementation. 

Upon comparing the two designs, it is evident that assignment_2 lacked an AIPlayer class, which was later introduced in assignment_3. The omission of AIPlayer in the intial design was a limitation because the problem statement required a computer controlled player. The addition of AIPlayer allowed the game to function without requiring human input at every turn, enhancing gameplay automation. 

Coversely, no unncecessary classes were present in assignment_2. The core structure of Game, Board, Tile, Player and PropertTile remained intact across both assignments. Demonstrating that the original design captured the fundamental components of the game. However, refinedments were needed to optimize the responsibilities of player subclass to accommodate AI behavior. 

While the calss names remained largely consistent between both versions, minor refinements were made to improve calrity. The addition of AIPlayer followed a logical naming convention that aligned with Humanplayer, ensuring consistency in subclassing. This highlights the importance of following clear and decscriptive naming convntions in Object-Oriented programming to maintain readability and scalability. 

In terms of realtionships, the primary differences was the introduction of AIPlayer as a subclass of player, which enhanced the class hierarachy without introducing unnecessary complexity. The method responsibilities were better divided, ensuring that HuamnPlayer relied on user innput, while AIPlayer exectued automated descisions. 

Had the initial design considered scalability earlier, fewer modifications would have been required during implementation. This highlights the importance of iterative refinement and the value of Agile development principles, where design and implementation co-evolve rather than being rigidly fixed at the outset.

A significant improvement in assignment_3 was the clearer use of associations and dependencies between objects. Initially, the player interactions were tightly coupled, relying on direct method callls that made future extensions difficult. The introduction of subclass specialization(HumanPlayer and AIPlayer) ensured that behvaiors were encapsulatedd approoriately within their respective classes, following the single Responsibility principle from the solid principles. 

Another aspect of complexity mangagments was the separation of UI interactions from core logic. In assignment_2, user interaction logic was closely linked to gameplay mechancis, making it harder to modify or extend. By refining the ConsoleUI interactions and ensuring that AIPlayer bypssess direct user input, the desing in assignment_3 became more mdodular and maintainable. 

While the initial design in assignment_2 laid a strong foundation,  it lacked foresight regarding AI implementation and class specialization. The working design in assignment_3 refines player interactions, introduces AI functionality, and improve class relationships, making it more extensible, modular and aligned with object oriented programming best practicise. 

Had the initial design considered scalability earlier, fewer modifications would have been required during implementation. This highlights the importance of iterative refinement and the value of Agile development principles, where design and implementation co-evolve rather than being rigidly fixed at the outset.

The most valuable lesson from this experience is that designing first provides a structured blueprint, but flexibility is crucial for real-world implementation. Key takeaways include:

Encapsulation and subclassing: Assigning responsibilities correctly between HumanPlayer and AIPlayer reduced complexity.

Early consideration of extensibility: Had AIPlayer been anticipated in Assignment 2, modifications would have been minimized.

Iterative refinement is essential: Initial designs are rarely perfect; continuous evaluation leads to better solutions.

Clear class relationships improve maintainability: Well-defined dependencies and associations facilitate future enhancements.

The transition from Assignment 2 to Assignment 3 highlights the importance of iterative design in software engineering. While the initial design captured core mechanics, refinements in AI integration and class structure enhanced the system. This experience emphasizes adaptability and continuous learning to align design choices with evolving requirements.
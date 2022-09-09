# Patient Triage System

Overview

For this project, you will implement a futuristic automated emergency room (ER) triage system. Your system will receive data in the form of patient and injury pairs over time and automatically assign patients to doctors as the resources become available depending on the severity of the patient’s injury.

To accomplish this, you will implement a data structure called a priority queue – more specifically, a heap-based priority queue. A priority queue is similar to a stack or a queue in that it stores and serves data in a specific order. It differs from these two data structures in that the order of data insertion does not affect the order of data serving (though the data clearly must be added to the structure before it can be served). Instead, the order of data serving is based on a priority of each datum – in this scenario, the priority of each patient is commensurate with the severity of their injury or age of the patient (depending on the comparator being used). Importantly, a priority queue accomplishes this task in an efficient way. Despite this specific setting, your implementation will be generic enough to work with all types of data and could be applied to other real world problems.

# BuildTickets 

BuildTickets is a Minecraft server plugin designed to help staff members manage builds by submitting and tracking build ideas, managing builder assignments, and monitoring progress efficiently.

## Plugin Features

- Allows your build team to organize itself with build tickets.
- Toggle Minecraft block physics using a command in-game.
- Toggle build permission using a command in-game.
- Custom menus for Builders with QoL changes.
- A buildchat, builderstatus... to make communicating easier!
- Works with MySQL and Redis
And a lot of other things that will help builders!

# Permissions
### Basic Permissions
| Name | Permission                    | Description                       |
| :--------  |:------------------------------| :-------------------------------- |
| Build Mode       | `buildmode.toggle`            | Gives permission for the Build Mode command. |
| Build Chat       | `buildchat.see`               | Gives permission to use, see... the Build chat. |
| Build Code       | `buildchat.toggle`            | Toggles the Build chat. |
| Build Physics       | `buildphysics.toggle`         | Toggles the Minecraft physics, to make building advanced things easier. |
| Help Message       | `buildtickets.general.help`   | Gives permission to use the BuildTickets help command. |
| Open Ticket Gui       | `buildtickets.tickets.gui`    | Gives permission to open our BuildTickets menu. |
| Create Ticket       | `buildtickets.tickets.create` | Gives permission to create Tickets. |

### Ticket Permission (self)
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Change Ticket Priority       | `buildtickets.tickets.change_priority`       | Gives permission to change the tickets priority in the GUI. |
| Join Ticket       | `buildtickets.tickets.join`       | Gives permission to join tickets. |
| Join Without Help       | `buildtickets.tickets.join_without_help`       | Gives permission to join tickets without help. |
| Request Help       | `buildtickets.tickets.request_help`       | Gives permission to request help/support with your ticket. |
| Ticket Mark As Complete       | `buildtickets.tickets.complete.mark`       | Gives permission to mark tickets as completed. |
| Ticket Confirm As Complete       | `buildtickets.tickets.complete.confirm`       | Gives permission to confirm the completed Tickets. |
| Ticket Change Reason       | `buildtickets.tickets.change_reason`       | Gives permission to change ticket reasons. |
| Ticket Leave       | `buildtickets.tickets.leave`       | Gives permission to leave tickets while having it accepted. |
| Open Ticket Viewer       | `buildtickets.viewer.open`       | Gives permission to open-view the tickets. |

### Ticket Permission (other)
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Change Ticket Priority Other       | `buildtickets.tickets.other.change_priority`       | Gives permission to change ticket priority of other tickets. |
| Join Ticket Other       | `buildtickets.tickets.other.join`       | Gives permission to join other builders their tickets. |
| Join Other Without Help       | `buildtickets.tickets.other.join_without_help`       | Gives permission to join other builders tickets without help. |
| Request Help Other       | `buildtickets.tickets.other.request_help`       | Gives permission to request help from others. |
| Ticket Mark As Complete Other       | `buildtickets.tickets.other.complete.confirm`       | Gives permission to mark other builders their tickets as completed. |
| Ticket Confirm As Complete Other       | `buildtickets.tickets.other.change_reason`       | Gives permission to confirm completed tickets from other builders. |
| Ticket Change Reason Other       | `buildtickets.tickets.other.complete.confirm`       | Gives permission to change other builders their ticket reason. |
| ticket Leave Other       | `buildtickets.tickets.other.leave`       | Gives permission to leave other builders their tickets. |
| Open Ticket Viewer Other       | `buildtickets.viewer.other.open`       | Gives permission to open other builders their ticket viewers. |

### Ticket Permissions (panel)
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Open Ticket Panel       | `buildtickets.panel.open`       | Gives permission to open the Admin ticket panel. |
| Player Stats Panel       | `buildtickets.panel.player_stats`       | Gives permission to open the Admin player stats panel. |
| Active Tickets Panel       | `buildtickets.panel.active_tickets`       | Gives permission to open and manage the active tickets panel. |

## FAQ

#### Does the plugin work on 1.8?

Currently, the plugin supports 1.13+ versions. We might add 1.8 support in the future.

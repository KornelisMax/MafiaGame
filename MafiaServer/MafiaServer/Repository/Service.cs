using MafiaServer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Repository
{
    public class Service
    {
        public void KillPlayer(MafiaContext _context)
        {
            var votedPlayerId = _context.Votes
                                        .GroupBy(x => x.VotedPlayerId)
                                        .OrderByDescending(z => z.Count())
                                        .Take(1)
                                        .Select(t => t.Key)
                                        .FirstOrDefault();
            _context.Players
                    .Where(x => x.PlayerId == votedPlayerId)
                    .FirstOrDefault()
                    .IsAlive = false;
            var votes = _context.Votes.ToList();
            _context.Votes.RemoveRange(votes);
            _context.SaveChanges();

        }

        public string WinningSide(MafiaContext _context, string votingPlayer)
        {

            var currentRoom = _context.Players
                                        .Where(x => x.Name == votingPlayer)
                                        .FirstOrDefault().RoomId;

            var playersInSameRoom = _context.Players
                                            .Where(x => x.RoomId == currentRoom)
                                            .ToList();

            var winningSide = playersInSameRoom
                                            .GroupBy(x => x.Role)
                                            .OrderByDescending(z => z.Count())
                                            .Take(playersInSameRoom.Count)
                                            .Select(t => t.Key)
                                            .FirstOrDefault();
            return winningSide;
        }

        public int WhichSideWon(MafiaContext _context, string votingPlayer)
        {
            int flag;
            string mafia = "Mafia";
            string civil = "Civil";
            var currentRoom = _context.Players
                                            .Where(x => x.Name == votingPlayer)
                                            .FirstOrDefault().RoomId;

            var playersInSameRoom = _context.Players
                                                    .Where(x => x.RoomId == currentRoom)
                                                    .ToList();

            var isMafiaAlive = playersInSameRoom
                                                .Where(x => x.IsAlive == true && x.Role == mafia)
                                                .FirstOrDefault();

            if (isMafiaAlive == null)
            {
                flag = 2;
                return flag;
            }

            var isCivilAlive = playersInSameRoom
                                                .Where(x => x.IsAlive == true&& x.Role.Equals(civil))
                                                .FirstOrDefault();

            if (isCivilAlive == null)
            {
                flag = 1;
                return flag;
            }
            flag = 0;
            return flag;
        }
    }
}

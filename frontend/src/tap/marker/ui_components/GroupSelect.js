import React, { useState } from 'react';
import styles from './GroupSelect.module.css';
import { groupMarkers, groups } from '../data';
import Posting from '../../../common/userposting/Posting';

const GroupSelect = ({ onSelect }) => {
  const [curGroup, setCurGroup] = useState(0);
  const [curGroupId, setCurGroupId] = useState(0);
  const [isGroups, setIsGroups] = useState(false);
  const selectGroup = groupId => {
    const selectIndex = groups.findIndex(e => {
      return e.groupId === groupId;
    });
    setCurGroup(selectIndex);
    setCurGroupId(groupId);
    setIsGroups(false);
    const selectedGroup = groupMarkers.filter(marker => marker.groupId === groupId);
    if (typeof onSelect === 'function') {
      onSelect(selectedGroup);
    }
  };
  const openGroup = () => {
    setIsGroups(!isGroups);
  };
  return (
    <div className={styles.routeTap}>
      <div className="w-full flex flex-col items-center justify-center">
        <p onClick={openGroup} className={`${styles.routeTapItem} border p-2`}>
          {groups[curGroup].groupTitle}
        </p>
        {isGroups ? (
          <ul className={`${styles.routeTapItem} border`}>
            {groups.map((group, i) => {
              return (
                <li
                  onClick={() => {
                    selectGroup(group.groupId);
                  }}
                  key={i}
                  className="p-2 border border-b"
                >
                  {group.groupTitle}
                </li>
              );
            })}
          </ul>
        ) : null}
      </div>
    </div>
  );
};

export default GroupSelect;
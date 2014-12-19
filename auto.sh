#!/bin/bash
JONASDIR="/opt/pad/jonas-full-5.3.0" # TODO: set absolute path of jonas directory
DEPLOYDIR="$JONASDIR/deploy"
TARGETDIR="target"
CP="cp -f"
SEP="="
MAX_LINE=80
ERR=0

# Uncomment to debug
#set -x

help() {
    local ECHO="echo -e"
    $ECHO "Usage: $0 <option>"
    $ECHO "Build, clean, deploy the project.\n"
    $ECHO "Options :"
    $ECHO " j\tStart JOnAS"
    $ECHO " js\tStop JOnAS"
    $ECHO " jc\tClean JOnAS (remove all deployed files)"
    $ECHO " jr\tReset JOnAS (stop, clean, start)"
    $ECHO " b\tBuild the project"
    $ECHO " c\tClean the project"
    $ECHO " cb\tClean and build the project"
    $ECHO " d\tDeploy the project"
    $ECHO " cbd\tClean, build and deploy the project"
    $ECHO " h\tDisplay this help"
}

process_string() {
    local STR=$1
    local NB=$2
    local ODD=$3
    local i=0
    while [[ $i -lt $NB ]]; do
	STR="$SEP$STR$SEP"
	i=$((i+1))
    done
    # Add a SEP character at the end if the number of SEP is odd
    if [ "$ODD" -eq 1 ]; then
	STR="$STR$SEP"
    fi
    echo -e "$STR"
}

print_title() {
    local PARAM=" $1 "
    local SIZE_PARAM=${#PARAM}
    local SIZE_DIFF=$((MAX_LINE-SIZE_PARAM))
    local SIZE_SEP=$((SIZE_DIFF/2))
    local SIZE_IS_ODD=$((SIZE_DIFF%2))
    process_string "$PARAM" "$SIZE_SEP" "$SIZE_IS_ODD"
}

print_success() {
    echo -e "\e[0;32m$1\e[0m"
}

print_err() {
    echo -e "\e[0;31m$1\e[0m"
}

start_jonas() {
    $JONASDIR/bin/jonas -clean start
}

stop_jonas() {
    $JONASDIR/bin/jonas stop
}

clean_jonas() {
    rm -vrf $DEPLOYDIR/*.{ear,jar,war}
}

reset_jonas() {
    stop_jonas
    clean_jonas
    start_jonas
}

clean() {
    print_title "Cleaning..."
    mvn clean
    ERR=$?
    if [[ $ERR -eq 0 ]]; then
	print_success "Clean success"
    else
	print_err "Clean failed"
    fi
    print_title "Cleaning finished"
}

build() {
    print_title "Building..."
    mvn install
    ERR=$?
    if [[ $ERR -eq 0 ]]; then
	print_success "Build success"
    else
	print_err "Build failed"
    fi
    print_title "Build finished"
}

deployFile() {
    $CP $1 $DEPLOYDIR 2>/dev/null
    ERR=$?
    if [[ $ERR -eq 0 ]]; then
	print_success "Deployed: $1"
    else
	print_err "Could not deployed: $1"
    fi
}

deploy() {
    print_title "Deploying..."
    local nb=0
    for f in *; do
	if [[ -d "$f/$TARGETDIR" ]]; then
	    for file in $(ls -1 $f/$TARGETDIR/*.{jar,war,ear} 2>/dev/null); do
		deployFile "$file"
		local deployerr=$ERR
		if [[ $deployerr -eq 0 ]]; then
		    nb=$((nb+1))
		else
		    ERR=1
		fi
	    done
	fi
    done
    if [[ $nb -gt 0 ]]; then
	print_success "Deployed: $nb files"
    elif [[ $ERR -eq 0  ]]; then
	print_err "Nothing to deploy. Build the project before deploying it."
    fi
    print_title "Deployment finished"
}

abort_if_err() {
    if [[ $ERR -ne 0 ]]; then
	print_err "ABORTED"
	exit 1
    fi
}

# Open the script's directory (so it will work if you call it from another directory)
cd "$(dirname '$0')"

if [[ $# -eq 0 ]]; then
    help
    exit 1
fi

case "$1" in
    "j")
	start_jonas
	;;
    "js")
	stop_jonas
	;;
    "jc")
	clean_jonas
	;;
    "jr")
	reset_jonas
	;;
    "b")
	build
	;;
    "c")
	clean
	;;
    "cb")
	clean
	abort_if_err
	build
	;;
    "d")
	deploy
	;;
    "bd")
	build
	abort_if_err
	deploy
	;;
    "cbd")
	clean
	abort_if_err
	build
	abort_if_err
	deploy
	;;
    "h")
	help
	;;
    *)
	help
	exit 1
	;;
esac
exit 0
